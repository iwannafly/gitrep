package ru.nkz.ivcgzo.serverGenTalons;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.nkz.ivcgzo.serverManager.common.ISqlSelectExecutor;
import ru.nkz.ivcgzo.serverManager.common.ITransactedSqlExecutor;
import ru.nkz.ivcgzo.serverManager.common.SqlSelectExecutor;
import ru.nkz.ivcgzo.serverManager.common.TransactedSqlManager;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServerException;
import ru.nkz.ivcgzo.thriftGenTalon.Calend;
import ru.nkz.ivcgzo.thriftGenTalon.Ndv;
import ru.nkz.ivcgzo.thriftGenTalon.Norm;
import ru.nkz.ivcgzo.thriftGenTalon.Nrasp;
import ru.nkz.ivcgzo.thriftGenTalon.Rasp;
import ru.nkz.ivcgzo.thriftGenTalon.Spec;
import ru.nkz.ivcgzo.thriftGenTalon.Talon;
import ru.nkz.ivcgzo.thriftGenTalon.Vidp;
import ru.nkz.ivcgzo.thriftGenTalon.Vrach;

/**
 * @author Avdeev Alexander
 */
public class TestServerGenTalons {

    private String conn = String.format("jdbc:postgresql://%s:%s/%s",
            "10.0.0.242", "5432", "zabol");
    private ISqlSelectExecutor sse;
    private ITransactedSqlExecutor tse;
    private ServerGenTalons testServer;
    private static final int COUNT_CONNECTIONS = 1;

    @Rule
    public ExpectedException testException = ExpectedException.none();

    @Before
    public final void setUp() throws Exception {
        sse = new SqlSelectExecutor(conn, "postgres", "postgres");
        tse = new TransactedSqlManager(conn, "postgres", "postgres", COUNT_CONNECTIONS);
        testServer = new ServerGenTalons(sse, tse);
    }

    @Test
    public void testStart() {
    }

    @Test
    public void testStop() {
        //fail("Not yet implemented"); // TODO
    }

    @Test
    public void testServerRegPatient() {
        //fail("Not yet implemented"); // TODO
    }

    @Test
    public final void getAllSpecForPolikliniki_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cLpu = 201;
        java.util.List <Spec> testSpecList =
                testServer.getAllSpecForPolikliniki(cLpu);
        assertEquals("list size", expectedListSize, testSpecList.size());
    }

    @Test
    public final void getVrachForCurrentSpec_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 2;
        final int cLpu = 201;
        final String cdol = "9";
        java.util.List <Vrach> testVrachList =
                testServer.getVrachForCurrentSpec(cLpu, cdol);
        assertEquals("list size", expectedListSize, testVrachList.size());
    }

    @SuppressWarnings("deprecation")
    @Test
    public final void getCalendar_isValueCorrect()
            throws KmiacServerException, TException {
        final int nweek = 52;
        final long cdate = new Date(112, 0, 1).getTime();
        Calend testCalendar =
                testServer.getCalendar(cdate);
        assertEquals("list nweek value", nweek, testCalendar.getNweek());
    }

    @Test
    public final void getNorm_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cpodr = 5;
        final String cdol = "5";
        java.util.List <Norm> testNorm =
                testServer.getNorm(cpodr, cdol);
        assertEquals("list size", expectedListSize, testNorm.size());
    }

    @Test
    public final void getNdv_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cpodr = 5;
        final int pcodvrach = 6;
        final String cdol = "3";
        java.util.List <Ndv> testNdv =
                testServer.getNdv(cpodr, pcodvrach, cdol);
        assertEquals("list size", expectedListSize, testNdv.size());
    }

    @Test
    public final void getNrasp_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cpodr = 5;
        final int pcodvrach = 6;
        final String cdol = "3";
        final int cxema = 1;
        java.util.List <Nrasp> testNrasp =
                testServer.getNrasp(cpodr, pcodvrach, cdol, cxema);
        assertEquals("list size", expectedListSize, testNrasp.size());
    }

    @Test
    public final void getRasp_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cpodr = 5;
        final int pcodvrach = 6;
        final String cdol = "3";
        java.util.List <Rasp> testRasp =
                testServer.getRasp(cpodr, pcodvrach, cdol);
        assertEquals("list size", expectedListSize, testRasp.size());
    }

    @SuppressWarnings("deprecation")
    @Test
    public final void getTalon_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 1;
        final int cpodr = 201;
        final int pcodvrach = 6;
        final String cdol = "3";
        final long datap = new Date(112,0,30).getTime();
        java.util.List <Talon> testTalon =
                testServer.getTalon(cpodr, pcodvrach, cdol, datap);
        assertEquals("list size", expectedListSize, testTalon.size());
    }

    @Test
    public final void getVidp_isListSizeCorrect()
            throws KmiacServerException, TException {
        final int expectedListSize = 5;
        java.util.List <Vidp> testVidp =
                testServer.getVidp();
        assertEquals("list size", expectedListSize, testVidp.size());
    }
}
