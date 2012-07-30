package ru.nkz.ivcgzo.serverReception;

import java.io.File;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;

import ru.nkz.ivcgzo.configuration;
import ru.nkz.ivcgzo.serverManager.common.AutoCloseableResultSet;
import ru.nkz.ivcgzo.serverManager.common.ISqlSelectExecutor;
import ru.nkz.ivcgzo.serverManager.common.ITransactedSqlExecutor;
import ru.nkz.ivcgzo.serverManager.common.Server;
import ru.nkz.ivcgzo.serverManager.common.SqlModifyExecutor;
import ru.nkz.ivcgzo.serverManager.common.SqlSelectExecutor.SqlExecutorException;
import ru.nkz.ivcgzo.serverManager.common.thrift.TResultSetMapper;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServerException;
import ru.nkz.ivcgzo.thriftReception.Patient;
import ru.nkz.ivcgzo.thriftReception.PatientNotFoundException;
import ru.nkz.ivcgzo.thriftReception.Policlinic;
import ru.nkz.ivcgzo.thriftReception.PoliclinicNotFoundException;
import ru.nkz.ivcgzo.thriftReception.ReleaseTalonOperationFailedException;
import ru.nkz.ivcgzo.thriftReception.ReserveTalonOperationFailedException;
import ru.nkz.ivcgzo.thriftReception.Spec;
import ru.nkz.ivcgzo.thriftReception.SpecNotFoundException;
import ru.nkz.ivcgzo.thriftReception.Talon;
import ru.nkz.ivcgzo.thriftReception.TalonNotFoundException;
import ru.nkz.ivcgzo.thriftReception.ThriftReception;
import ru.nkz.ivcgzo.thriftReception.ThriftReception.Iface;
import ru.nkz.ivcgzo.thriftReception.Vidp;
import ru.nkz.ivcgzo.thriftReception.VidpNotFoundException;
import ru.nkz.ivcgzo.thriftReception.Vrach;
import ru.nkz.ivcgzo.thriftReception.VrachNotFoundException;

public class ServerReception extends Server implements Iface {

////////////////////////////////////////////////////////////////////////
//                          Fields                                    //
////////////////////////////////////////////////////////////////////////

    private TServer thrServ;

    private static Logger log = Logger.getLogger(ServerReception.class.getName());

//////////////////////////////// Mappers /////////////////////////////////

    private TResultSetMapper<Patient, Patient._Fields> rsmPatient;
    private TResultSetMapper<Policlinic, Policlinic._Fields> rsmPoliclinic;
    private TResultSetMapper<Spec, Spec._Fields> rsmSpec;
    private TResultSetMapper<Vrach, Vrach._Fields> rsmVrach;
    private TResultSetMapper<Vidp, Vidp._Fields> rsmVidp;
    private TResultSetMapper<Talon, Talon._Fields> rsmTalon;

//////////////////////////// Field Name Arrays ////////////////////////////

    private static final String[] PATIENT_FIELD_NAMES = {
        "npasp", "fam", "im", "ot", "datar", "poms_ser", "poms_nom"
    };
    private static final String[] POLICLINIC_FIELD_NAMES = {
        "pcod", "name"
    };
    private static final String[] SPEC_FIELD_NAMES = {
        "pcod", "name"
    };
    private static final String[] VIDP_FIELD_NAMES = {
        "pcod", "name", "vcolor"
    };
    private static final String[] VRACH_FIELD_NAMES = {
        "pcod", "fam", "im", "ot"
    };
    private static final String[] TALON_FIELD_NAMES = {
        "id", "ntalon", "vidp", "timepn", "timepk", "datap", "npasp", "dataz", "prv"
    };

////////////////////////////////////////////////////////////////////////
//                         Constructors                               //
////////////////////////////////////////////////////////////////////////

    public ServerReception(final ISqlSelectExecutor sse, final ITransactedSqlExecutor tse) {
        super(sse, tse);

        //Инициализация логгера с конфигом из файла ../../manager/log4j.xml;
        String manPath = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().getPath()).getParentFile().getParentFile().getAbsolutePath();
        DOMConfigurator.configure(new File(manPath, "log4j.xml").getAbsolutePath());

        rsmPatient = new TResultSetMapper<>(Patient.class, PATIENT_FIELD_NAMES);
        rsmPoliclinic = new TResultSetMapper<>(Policlinic.class, POLICLINIC_FIELD_NAMES);
        rsmSpec = new TResultSetMapper<>(Spec.class, SPEC_FIELD_NAMES);
        rsmVidp = new TResultSetMapper<>(Vidp.class, VIDP_FIELD_NAMES);
        rsmVrach = new TResultSetMapper<>(Vrach.class, VRACH_FIELD_NAMES);
        rsmTalon = new TResultSetMapper<>(Talon.class, TALON_FIELD_NAMES);
    }

////////////////////////////////////////////////////////////////////////
//                       Private Methods                              //
////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////
//                       Public Methods                               //
////////////////////////////////////////////////////////////////////////

//////////////////////// Start/Stop Methods /////////////////////////////

    @Override
    public final void start() throws Exception {
        ThriftReception.Processor<Iface> proc =
                new ThriftReception.Processor<Iface>(this);
        thrServ = new TThreadedSelectorServer(new Args(
                new TNonblockingServerSocket(configuration.thrPort)).processor(proc));
        log.info("Start serverReception");
        thrServ.serve();
    }

    @Override
    public final void stop() {
        if (thrServ != null) {
            thrServ.stop();
            log.info("Stop serverReception");
        }
    }

//////////////////// Configuration Methods /////////////////////////////

    @Override
    public void testConnection() throws TException {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveUserConfig(final int id, final String config) throws TException {
        // TODO Auto-generated method stub
    }

/////////////////////// Select Methods //////////////////////////////////

    @Override
    public final Patient getPatient(final String omsSer, final String omsNum)
            throws KmiacServerException, PatientNotFoundException, TException {
        final String sqlQuery = "SELECT npasp, fam, im, ot, datar, poms_ser, poms_nom "
                + "FROM patient WHERE poms_ser = ? AND poms_nom = ?;";
        try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlQuery, omsSer, omsNum)) {
            if (acrs.getResultSet().next()) {
                return rsmPatient.map(acrs.getResultSet());
            } else {
                throw new PatientNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final List<Policlinic> getPoliclinic() throws KmiacServerException,
            PoliclinicNotFoundException, TException {
        final String sqlQuery = "SELECT DISTINCT n_n00.pcod, n_n00.name FROM n_n00 "
                + "INNER JOIN e_talon ON n_n00.pcod = e_talon.cpol;";
        try (AutoCloseableResultSet acrs = sse.execQuery(sqlQuery)) {
            List<Policlinic> tmpList = rsmPoliclinic.mapToList(acrs.getResultSet());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                throw new PoliclinicNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final List<Spec> getSpec(final int cpol) throws KmiacServerException,
            SpecNotFoundException, TException {
        final String sqlQuery = "SELECT DISTINCT n_s00.pcod, n_s00.name FROM n_s00 "
                + "INNER JOIN e_talon ON n_s00.pcod = e_talon.cdol "
                + "WHERE e_talon.cpol = ? AND e_talon.prv = ?;";
        try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlQuery, cpol, 0)) {
            List<Spec> tmpList = rsmSpec.mapToList(acrs.getResultSet());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                throw new SpecNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final List<Vrach> getVrach(final int cpol, final String cdol)
            throws KmiacServerException, VrachNotFoundException, TException {
        final String sqlQuery = "SELECT DISTINCT s_vrach.pcod, s_vrach.fam, s_vrach.im, s_vrach.ot "
                + "FROM s_vrach INNER JOIN e_talon ON s_vrach.pcod = e_talon.pcod_sp "
                + "WHERE e_talon.cpol = ? AND e_talon.cdol = ?;";
        try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlQuery, cpol, cdol)) {
            List<Vrach> tmpList = rsmVrach.mapToList(acrs.getResultSet());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                throw new VrachNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final List<Vidp> getVidp() throws KmiacServerException,
            VidpNotFoundException, TException {
        final String sqlQuery = "SELECT pcod, name, vcolor FROM e_vidp";
        try (AutoCloseableResultSet acrs = sse.execQuery(sqlQuery)) {
            List<Vidp> tmpList = rsmVidp.mapToList(acrs.getResultSet());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                throw new VidpNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final List<Talon> getTalon(final int cpol, final String cdol, final int pcod)
            throws KmiacServerException, TalonNotFoundException, TException {
        final int prv = 0;
        // java.sql.Date не имеет нулевого конструктора, а preparedQuery() не работает с
        // java.util.Date. Поэтому для передачи сегодняшней даты требуется такой велосипед.
        final long todayMillisec = new java.util.Date().getTime();
        final String sqlQuery = "SELECT id, ntalon, vidp, timepn, timepk, datap, npasp, dataz, prv "
                + "FROM e_talon WHERE cpol = ? AND cdol = ? AND pcod_sp = ? AND datap >= ? "
                + "AND prv = ?;";
        try (AutoCloseableResultSet acrs =
                sse.execPreparedQuery(sqlQuery, cpol, cdol, pcod, new Date(todayMillisec), prv)) {
            List<Talon> tmpList = rsmTalon.mapToList(acrs.getResultSet());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                throw new TalonNotFoundException();
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException(e.getMessage());
        }
    }

    @Override
    public final void reserveTalon(final Patient pat, final Talon talon)
            throws KmiacServerException, ReserveTalonOperationFailedException,
            TException {
        final int prv = 2;
        // java.sql.Date не имеет нулевого конструктора, а preparedUpdate() не работает с
        // java.util.Date. Поэтому для передачи сегодняшней даты требуется такой велосипед.
        final long todayMillisec = new java.util.Date().getTime();
        final String sqlQuery = "UPDATE e_talon SET npasp = ?, dataz = ?, prv = ? "
                + "WHERE  id = ?;";
        try (SqlModifyExecutor sme = tse.startTransaction()) {
            final int numUpdated = sme.execPreparedUpdate(
                    sqlQuery, false, pat.getId(), new Date(todayMillisec), prv, talon.getId());
            if (numUpdated == 1) {
                sme.setCommit();
            } else {
                sme.rollbackTransaction();
                throw new ReserveTalonOperationFailedException();
            }
        } catch (SqlExecutorException | InterruptedException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

    @Override
    public final void releaseTalon(final Talon talon) throws KmiacServerException,
            ReleaseTalonOperationFailedException, TException {
        final int defaultNpasp = 0;
        final int defaultPrv = 0;
        final String sqlQuery = "UPDATE e_talon SET npasp = ?, dataz = ?, prv = ? "
                + "WHERE  id = ?;";
        try (SqlModifyExecutor sme = tse.startTransaction()) {
            final int numUpdated = sme.execPreparedUpdate(
                    sqlQuery, false, defaultNpasp, null, defaultPrv, talon.getId());
            if (numUpdated == 1) {
                sme.setCommit();
            } else {
                sme.rollbackTransaction();
                throw new ReleaseTalonOperationFailedException();
            }
        } catch (SqlExecutorException | InterruptedException e) {
            log.log(Level.ERROR, "SQL Exception: ", e);
            throw new KmiacServerException();
        }
    }

}
