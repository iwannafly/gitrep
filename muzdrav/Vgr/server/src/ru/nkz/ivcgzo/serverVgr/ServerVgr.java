package ru.nkz.ivcgzo.serverVgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;

import ru.nkz.ivcgzo.configuration;
import ru.nkz.ivcgzo.serverManager.common.AutoCloseableResultSet;
import ru.nkz.ivcgzo.serverManager.common.DbfMapper;
import ru.nkz.ivcgzo.serverManager.common.ISqlSelectExecutor;
import ru.nkz.ivcgzo.serverManager.common.ITransactedSqlExecutor;
import ru.nkz.ivcgzo.serverManager.common.Server;
import ru.nkz.ivcgzo.serverManager.common.SqlSelectExecutor.SqlExecutorException;
import ru.nkz.ivcgzo.serverManager.common.thrift.TResultSetMapper;
import ru.nkz.ivcgzo.thriftCommon.classifier.IntegerClassifier;
import ru.nkz.ivcgzo.thriftCommon.kmiacServer.KmiacServerException;
import ru.nkz.ivcgzo.thriftVgr.RdPatient;
import ru.nkz.ivcgzo.thriftVgr.RdConVizit;
import ru.nkz.ivcgzo.thriftVgr.RdVizit;
import ru.nkz.ivcgzo.thriftVgr.KartaBer;
import ru.nkz.ivcgzo.thriftVgr.Kontidi;
import ru.nkz.ivcgzo.thriftVgr.Kontiis;
import ru.nkz.ivcgzo.thriftVgr.Kontilo;
import ru.nkz.ivcgzo.thriftVgr.Kontios;
import ru.nkz.ivcgzo.thriftVgr.Kontipa;
import ru.nkz.ivcgzo.thriftVgr.KovNotFoundException;
import ru.nkz.ivcgzo.thriftVgr.Lgot;
import ru.nkz.ivcgzo.thriftVgr.RdConVizit;
import ru.nkz.ivcgzo.thriftVgr.RdPatient;
import ru.nkz.ivcgzo.thriftVgr.RdVizit;
import ru.nkz.ivcgzo.thriftVgr.ThriftVgr;
import ru.nkz.ivcgzo.thriftVgr.ThriftVgr.Iface;
import ru.nkz.ivcgzo.thriftVgr.ThriftVgr;
import ru.nkz.ivcgzo.thriftVgr.ThriftVgr.Iface;
import sun.security.util.Length;


public class ServerVgr extends Server implements Iface {
	private TServer thrServ;
	private static Logger log = Logger.getLogger(ServerVgr.class.getName());
	private final TResultSetMapper<Kontidi, Kontidi._Fields> rsmKontidi;
	private final Class<?>[] KontidiTypes;
	private final TResultSetMapper<Kontiis, Kontiis._Fields> rsmKontiis;
	private final Class<?>[] KontiisTypes;
	private final TResultSetMapper<Kontilo, Kontilo._Fields> rsmKontilo;
	private final Class<?>[] KontiloTypes;
	private final TResultSetMapper<Kontipa, Kontipa._Fields> rsmKontipa;
	private final Class<?>[] KontipaTypes;
	private final TResultSetMapper<Kontios, Kontios._Fields> rsmKontios;
	private final Class<?>[] KontiosTypes;
	private final TResultSetMapper<Lgot, Lgot._Fields> rsmLgot;
	private final Class<?>[] LgotTypes;
	
	private final TResultSetMapper<RdPatient, RdPatient._Fields> rsmRdPat;
	private final Class<?>[] rdPatientTypes;
	private final TResultSetMapper< RdVizit, RdVizit._Fields> rsmRdViz;
	private final Class<?>[] rdVizitTypes;
	private final TResultSetMapper< RdConVizit, RdConVizit._Fields> rsmRdCV;
	private final Class<?>[] rdConVizitTypes;

	
	
	public ServerVgr(ISqlSelectExecutor sse, ITransactedSqlExecutor tse) {
		super(sse, tse);
		
		rsmKontidi = new TResultSetMapper<>(Kontidi.class, "bn",     "isd",   "pp",    "priz",  "prizi" );
		KontidiTypes = new Class<?>[] {               Integer.class,String.class,String.class,Integer.class,Integer.class};
		rsmKontiis = new TResultSetMapper<>(Kontiis.class, "bn",     "kissl",   "dvi" );
		KontiisTypes = new Class<?>[] {               Integer.class,String.class,Date.class};
		rsmKontilo = new TResultSetMapper<>(Kontilo.class, "bn",     "klo",   "dn",    "dk",  "ter","lpu" );
		KontiloTypes = new Class<?>[] {               Integer.class,String.class,Date.class,Date.class,Integer.class,Integer.class};
		rsmKontios = new TResultSetMapper<>(Kontios.class, "bn",     "kspec",   "dvo" );
		KontiosTypes = new Class<?>[] {               Integer.class,Integer.class,Date.class};
		rsmKontipa = new TResultSetMapper<>(Kontipa.class, "bn",     "fam",      "im",       "otch",      "sex",      "dr",       "stat",        "ul", "nd","nk","kat",                               "sth","polis","pud","sdog","dpp","ter","lpu","dotkr",                                      "nas","nnas","dri","gri",                           "pp","konti","datasm" );
		KontipaTypes = new Class<?>[] {               Integer.class,String.class,String.class,String.class,String.class,Date.class,Integer.class,String.class,String.class,String.class,Integer.class,Integer.class,String.class,Integer.class,Date.class,Integer.class,Integer.class,Date.class,Integer.class,String.class,Date.class,Integer.class,String.class,String.class,Date.class};
		rsmLgot = new TResultSetMapper<>(Lgot.class, "bn",     "klg" );
		LgotTypes = new Class<?>[] {               Integer.class,Integer.class};
		
		rsmRdPat = new TResultSetMapper<>(RdPatient.class,"uid","npasp"      ,"fam"       ,"im"        ,"ot"        ,"datar"   ,"docser"    ,"docnum"    ,"tawn"       ,"street"    ,"house"     ,"flat"      ,"poms_ser"  ,"poms_nom"  ,"dog"       ,"stat"       ,"lpup"       ,"terp"       ,"ftawn"      ,"fstreet"   ,"fhouse"    ,"fflat"     ,"grk"       ,"rez"       ,"telm"      ,"vred"      ,"deti"       ,"datay"   ,"yavka1"     ,"datazs"  ,"famv"      ,"imv"       ,"otv"       ,"datasn"  ,"shet"       ,"kolrod"     ,"abort"      ,"vozmmen"    ,"prmen"      ,"datam"   ,"kont"       ,"dsp"        ,"dsr"        ,"dtroch"     ,"cext"       ,"indsol"     ,"vitae"     ,"allerg"    ,"ishod"      ,"prrod"     ,"oslrod"     ,"sem"        ,"rost"       ,"vesd"      ,"osoco"      ,"uslpr"      ,"dataz"   ,"polj"       ,"obr",       "fiootec",   "mrabotec",   "telotec",   "grotec",   "photec",    "vredotec",   "vozotec",     "mrab",     "prof",       "eko",        "rub",        "predp",       "terpr",       "oblpr",      "diag",       "cvera",      "dataosl", "osp");
		rdPatientTypes = new Class<?>[]{          Integer.class,Integer.class,String.class,String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,Integer.class,Integer.class,Integer.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,Integer.class,Date.class,Integer.class,Date.class,String.class,String.class,String.class,Date.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Date.class,Boolean.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,String.class,String.class,Integer.class,String.class,Integer.class,Integer.class,Integer.class,Double.class,Integer.class,Integer.class,Date.class,Integer.class,Integer.class,String.class,String.class,String.class,String.class,String.class,Integer.class,Integer.class,String.class,String.class,Boolean.class,Boolean.class,Boolean.class, Integer.class, Integer.class,Integer.class,Integer.class,Date.class,Integer.class};
		rsmRdViz = new TResultSetMapper<>(RdVizit.class,"uid",                "dv",         "sp",     "famvr",     "imvr",      "otvr",       "diag",        "mso",         "rzp",         "aim",          "npr",       "npasp");
		rdVizitTypes = new Class<?>[]{                   Integer.class, Date.class,Integer.class,String.class,String.class,String.class,String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class};
	
		rsmRdCV = new TResultSetMapper<>(RdConVizit.class,"uiv",         "uid",      "npasp",        "ned",        "ves",      "lcad",       "ldad",       "rcad",       "rdad",       "ball",        "hdm",        "spl",         "oj",         "chcc",       "polpl",      "predpl",       "serd",        "serd1",       "oteki");
		rdConVizitTypes = new Class<?>[]{          Integer.class,Integer.class,Integer.class,Integer.class,Double.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class };
		

		// TODO Auto-generated constructor stub
	}

	@Override
	public void testConnection() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUserConfig(int id, String config) throws TException {
		// TODO Auto-generated method stub

	}
//////////////////////////Classifiers ////////////////////////////////////

@Override
public final List<IntegerClassifier> getAllPolForCurrentLpu(final int lpuId) throws TException {
final String sqlQuery = "SELECT pcod, name FROM n_n00 WHERE clpu = ?";
final TResultSetMapper<IntegerClassifier, IntegerClassifier._Fields> rsmN00 =
new TResultSetMapper<>(IntegerClassifier.class, "pcod", "name");
try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlQuery, lpuId)) {
return rsmN00.mapToList(acrs.getResultSet());
} catch (SQLException e) {
log.log(Level.ERROR, "SQl Exception: ", e);
throw new TException(e);
}
}

@Override
public final List<IntegerClassifier> getPolForCurrentLpu(final int polId) throws TException {
final String sqlQuery = "SELECT pcod, name FROM n_n00 WHERE pcod = ?";
final TResultSetMapper<IntegerClassifier, IntegerClassifier._Fields> rsmN00 =
new TResultSetMapper<>(IntegerClassifier.class, "pcod", "name");
try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlQuery, polId)) {
return rsmN00.mapToList(acrs.getResultSet());
} catch (SQLException e) {
log.log(Level.ERROR, "SQl Exception: ", e);
throw new TException(e);
}
}


	@Override
	public void start() throws Exception {
		ThriftVgr.Processor<Iface> proc = new ThriftVgr.Processor<Iface>(this);
		thrServ = new TThreadedSelectorServer(new Args(new TNonblockingServerSocket(configuration.thrPort)).processor(proc));
		thrServ.serve();
		log.info("Start serverVgr");
	}

	@Override
	public void stop() {
		if (thrServ != null) thrServ.stop();
		log.info("Stop serverVgr");
	}


	@Override
	public List<RdVizit> getRdVizit() throws KmiacServerException, TException {
		try (AutoCloseableResultSet acrs = sse.execPreparedQuery("SELECT v.datap as dv,v.diag ,v.mobs as mso,v.rezult as rzp,s.cod_sp as sp,c.cod_tf as aim,d.fam as famvr,d.im as imvr,d.ot as otvr,rd.npasp,v.id as uiv,v.n_sp as npr "+
"FROM p_rd_sl rd,p_vizit_amb v,n_s00 s,s_vrach d,n_p0c c "+ 
"where rd.npasp=v.npasp and rd.id_pvizit=v.id_obr and v.cdol=s.pcod and v.cod_sp=d.pcod and v.cpos=c.pcod "+ 
"order by rd.id_pvizit")) {
			if (acrs.getResultSet().next())
				return rsmRdViz.mapToList(acrs.getResultSet());
			else
				throw new KmiacServerException("нет записи");
		} catch (SQLException e) {
			((SQLException) e.getCause()).printStackTrace();
			throw new KmiacServerException();
		}
		
	}

	@Override
	public List<RdConVizit> getRdConVizit() throws KmiacServerException,
			TException {
		try (AutoCloseableResultSet acrs = sse.execPreparedQuery("select d.id_pvizit as uid,d.id_pos as uiv,d.npasp,d.srok as ned,d.ves,d.art1 as lcad,d.art2 as ldad,d.art3 as rcad,d.art4 as rdad, "+
"d.ball,d.hdm,d.spl,d.oj,d.chcc,d.polpl,d.predpl,d.serd,d.serd1,d.oteki "+ 
"from p_rd_din d,p_rd_sl rd "+ 
"where d.id_pvizit=rd.id_pvizit")) {
			if (acrs.getResultSet().next())
				return rsmRdCV.mapToList(acrs.getResultSet());
			else
				throw new KmiacServerException("нет записи");
		} catch (SQLException e) {
			((SQLException) e.getCause()).printStackTrace();
			throw new KmiacServerException();
		}
	}

	@Override
	public List<ru.nkz.ivcgzo.thriftVgr.RdPatient> getRdPatient()
			throws KmiacServerException, TException {
		try (AutoCloseableResultSet acrs = sse.execQuery("SELECT rd.id_pvizit as uid,rd.npasp,p.fam as fam, p.im as im, p.ot as ot, p.datar,p.docser,p.docnum,p.adp_gorod as tawn,p.adp_ul as street,  "+
"p.adp_dom as house,p.adp_kv as flat,p.poms_ser,p.poms_nom,p.poms_ndog as dog,a.stat,n.clpu as lpup,p.terp,p.adm_gorod as ftawn, p.adm_ul as fstreet, "+ 
"p.adm_dom as fhouse, p.adm_kv as fflat,s.grup as grk,s.ph as rez, p.tel as telm,s.vred,rd.deti,rd.datay,rd.yavka1,rd.datazs, "+
"vr.fam as famv,vr.im as imv,vr.ot as otv,rd.datasn,rd.shet,rd.kolrod,rd.abort,rd.vozmen,rd.prmen,rd.datam,rd.kont, "+ 
"rd.dsp,rd.dsr,rd.dtroch,rd.cext,rd.indsol,s.vitae,s.allerg,rd.ishod,rd.prrod,rd.oslrod,i.sem, "+ 
"rd.rost,rd.vesd,i.osoco,i.uslpr,rd.dataz,rd.polj,z0.kod_tf as obr, "+ 
"i.fiootec,i.mrotec as mrabotec,i.telotec,i.grotec,i.photec,i.vredotec,i.votec as vozotec,p.name_mr as mrab,p.prof, "+ 
"rd.eko,rd.rub,rd.predp,p.ter_liv as terpr,p.region_liv as oblpr,rd.cdiagt as diag,rd.cvera,rd.dataosl,rd.osp "+ 
"FROM patient p,p_rd_sl rd,p_rd_inf i,p_sign s,p_vizit v,n_az9 a,n_n00 n,s_vrach vr,n_z00 z0 "+ 
"WHERE  p.cpol_pr=n.pcod and v.npasp=s.npasp and i.npasp=s.npasp and "+ 
"p.npasp=s.npasp and p.sgrp=a.pcod and rd.npasp=p.npasp and v.id=rd.id_pvizit "+
"and v.cod_sp=vr.pcod and z0.pcod_s=i.obr ")) {
						if (acrs.getResultSet().next())
							return rsmRdPat.mapToList(acrs.getResultSet());
						else
							throw new KmiacServerException("нет записи");
					} catch (SQLException e) {
						((SQLException) e.getCause()).printStackTrace();
						throw new KmiacServerException();
					}
				}


	@Override
	public String formfilecsv() throws KmiacServerException,
			TException {
		// TODO Auto-generated method stub
		Date p1; Date p2; Date p3; Date p4; Date p5; Date p6; Date p7;
		Date p8;		Date p9; Date p10;
		Integer ball1;Integer ball2;Integer ball3;
		Integer ball4;Integer ball5;String grk;Integer tawn=0;Integer ftawn=0;
		Integer kod2; Integer kod3; Integer kod4;Integer kod5;
		Integer kod6; Integer kod7; Integer kod8; Integer kod9;
		Integer j = 0;Integer hr; Integer disp1;
		Integer risk ;Integer kontr;Integer rod;
		Integer grot; Integer hsm; Integer hal; Integer hdr;
		Integer pr; Integer ek; Integer ru;Integer otec; Integer iw2;
        Integer k1; Integer k2;Integer k3;Integer k4;
        Integer k5; Integer k6;Integer k7;Integer k8;
        Integer k10; Integer k9;
        double ves;
		String dex1 = "";
        String dex2 = "";
        String dex3 = "";
        String dex4 = "";
        String dex5 = "";
        String dex6 = "";
        String dex7 = "";
        String dex9 = "";
        String dex10 = "";
        String dex = "";
        String dak = "";
        String dsost = "";
        String dosl = "";
//        BigInteger fam = null;
//        BigInteger im = null;
//        BigInteger otsh = null;
		AutoCloseableResultSet acrs = null, acrs2 = null;
		//таблица паспортной информации Patient.csv
		StringBuilder sb = new StringBuilder(0x10000);
//		Base64.BASE64DEFAULTLENGTH.
		try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("c:\\patient.htm"), "utf-8")) {
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb.append("<head>");
			sb.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb.append("<title>Паспортные данные</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("uid;fam;im;ot;dr;pasp;terpr;oblpr;tawn;street;house;flat;polis;dog;stat;lpup;ter;obl;terp;ftown;fstreet;fhouse;fflat;adr;grk;rez");
		//Vizit.csv
		StringBuilder sb1 = new StringBuilder(0x10000);
		sb1.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb1.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb1.append("<head>");
			sb1.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb1.append("<title>Посещения</title>");
		sb1.append("</head>");
		sb1.append("<body>");
		sb1.append("uiv;uid;dv;sp;wr;diap;mso;rzp;aim;npr");
		// Con_vizit.scv
		StringBuilder sb2 = new StringBuilder(0x10000);
		sb2.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb2.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb2.append("<head>");
			sb2.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb2.append("<title>Динамическое наблюдение</title>");
		sb2.append("</head>");
		sb2.append("<body>");
		sb2.append("uicv;uiv;uid;ves;ned;dno;plac;lcad;ldad;rcad;rdad;ball1;ball2;ball3;ball4;ball5;nexdate;cirkumference;css;polojpl;predpl;cerdpl;cerdpl2;oteki;otekiras");
		//Con_diagn.csv
		StringBuilder sb3 = new StringBuilder(0x10000);
		sb3.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb3.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb3.append("<head>");
			sb3.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb3.append("<title>Соматические диагнозы</title>");
		sb3.append("</head>");
		sb3.append("<body>");
		sb3.append("ndiag;uid;dex1;dex2;dex3;dex4dex5;dex6;dex7;dex9;dex10;dex;dak;dsost;dosl");
		// Con_main.csv
		StringBuilder sb4 = new StringBuilder(0x10000);
		sb4.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb4.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb4.append("<head>");
			sb4.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb4.append("<title>Особенности течения</title>");
		sb4.append("</head>");
		sb4.append("<body>");
		sb4.append("num;uid;jdet;dvzdu;srokvzu1;grisk;dgrisk;drodr;fiovr;dred;telm;dsndu;nber;nrod;job;vp;vn;circl;hfio;hmrab;htel;hgrk;hrez;hsm;hal;hdr;hhealth;hage;mrab;dolj;dlm;kontr;dsp;dcr;dtroch;cext;solov;cs;allerg;nasl;gemotr;prich;dprich;predp;cdiag;cvera;eko;dvpl;rub");
        //  Con_sob
		StringBuilder sb5 = new StringBuilder(0x10000);
		sb5.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb5.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb5.append("<head>");
			sb5.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb5.append("<title>Социально-гигиенические факторы</title>");
		sb5.append("</head>");
		sb5.append("<body>");
		sb5.append("nsob;uid;obr;sem;height;weight;priv;prof;proj;osl;ak;eks;gen;sost;point1;point2;point3;point4;point5;sob_date");
		
		StringBuilder sb6 = new StringBuilder(0x10000);
		sb6.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		sb6.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb6.append("<head>");
			sb6.append("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
			sb6.append("<title>Диагнозы</title>");
		sb6.append("</head>");
		sb6.append("<body>");
		sb6.append("numd;uid;uid_pol;ddiag;spz;diag;dpdiag;un;vp");
//		for (int j = 0; j < rdPatient.size(); j++) {
//			RdPatient rdp = rdPatient.get(j);
//			
//		}
		List<RdPatient> rdPatient = getRdPatient();
		j = 0;
		for (RdPatient rdp : rdPatient) {
			j = j+1;
		kod2 =0 ; kod3 =0 ; kod4 = 0; kod5 = 0; kod6 = 0; 
		kod7 = 0; kod8 = 0; kod9 = 0;	
		ball1 = 0;ball2 = 0;ball3 = 0;
		ball4 = 0;ball5 = 0;grk = "0";
		k1 = 0; k2 = 0; k3 = 0; k4 = 0;
		k5 = 0; k6 = 0; k7 = 0; k8 = 0;k10 = 0; k9 = 0;
       dex1 = "";dex2 = ""; dex3 = ""; dex4 = ""; dex5 = "";
        dex6 = ""; dex7 = "";dex9 = ""; dex10 = "";
        dex = "";  dak = ""; dsost = ""; dosl = "";
		System.out.println(rdp);		
		p7 = new Date(rdp.datar);
		grk=rdp.grk;
		if (rdp.grk.equals("I")) grk = "1";
		if (rdp.grk.equals("II")) grk = "2";
		if (rdp.grk.equals("III")) grk = "3";
		if (rdp.grk.equals("IV")) grk = "4";
		p1 = new Date(rdp.datay);
		p2 = new Date(rdp.dataz);
		p3 = new Date(rdp.datazs);
		p4 = new Date(rdp.dataz);
		p5 = new Date(rdp.datasn);
		p6 = new Date(rdp.datam);
		p8 = new Date(System.currentTimeMillis());
		try (AutoCloseableResultSet acrs21 = sse.execPreparedQuery("select kdnpt from n_l00  where nam_kem=?",rdp.tawn)) {
			if (acrs21.getResultSet().next()){
			tawn = acrs21.getResultSet().getInt(1);
			}
			} catch (SQLException e) {
				((SQLException) e.getCause()).printStackTrace();
				throw new KmiacServerException();
			}
		try (AutoCloseableResultSet acrs21 = sse.execPreparedQuery("select kdnpt from n_l00  where nam_kem=?",rdp.ftawn)) {
			if (acrs21.getResultSet().next()){
			ftawn = acrs21.getResultSet().getInt(1);
			}
			} catch (SQLException e) {
				((SQLException) e.getCause()).printStackTrace();
				throw new KmiacServerException();
			}
		risk = 0;kontr = 0;	rod = 0;
		grot = 0; hsm = 0; hdr = 0; hal = 0;
		pr = 0; ek = 0; ru = 0;
		if (rdp.prrod != "") rod =1;
		if (rdp.kont) kontr = 1;
		if (rdp.predp) pr = 1;
		if (rdp.eko) ek = 1;
		if (rdp.rub) ru = 1;
		if (rdp.grotec.length()==1) grot = 1;
		else	{ if(rdp.grotec.length()==3) grot = 3;
		else{
		if (rdp.grotec.substring(0, 2).equals("II")) grot = 2;
		if (rdp.grotec.substring(0, 2).equals("IV")) grot = 4;}}
		otec = rdp.vredotec;
		if ((otec-4)<0){
		hdr=0; iw2=otec;
		}else {
		hdr=1; iw2=otec-4;	
		}
		if ((iw2-2)<0){
		hal=0; 
		}else {
		hsm=1; iw2=iw2-2;	
		}
		hsm=iw2;
		if (rdp.vred != null)
//		{System.out.println(rdp.vred);		
		if (rdp.vred.charAt(0) == '1') kod2= kod2+1;
		if (rdp.vred.charAt(1) == '1') kod2= kod2+2;
		if (rdp.vred.charAt(2) == '1') kod2= kod2+4;
		if (rdp.vred.charAt(3) == '1') kod2= kod2+8;

//		Date dgrisk = null;
		sb4.append("<br>");
		sb4.append(String.format("%d;%d;%d;%td.%4$tm.%4$tY;%d;%d;%td.%7$tm.%7$tY;%td.%8$tm.%8$tY;%s %s %s;%td.%12$tm.%12$tY;%s;", j,rdp.npasp,rdp.deti,p1,rdp.yavka1,risk,p8,p3,rdp.famv,rdp.imv,rdp.otv,p4,rdp.telm));
        if (rdp.datasn != 0)
		sb4.append(String.format("%td.%1$tm.%1$tY;%d;%d;%d;%d;%d;%d;%s;%s;%s;%d;%s;%d;%d;%d;;%d;%s;%s;%td.%19$tm.%19$tY;%d;%d;%d;%d;%d;%d;%s;%s;;;%d;",p5,rdp.shet,rdp.kolrod,rdp.abort,rdp.polj,rdp.vozmen,rdp.prmen,rdp.fiootec,rdp.mrabotec,rdp.telotec,grot,rdp.photec,hsm,hal,hdr,rdp.vozotec,rdp.mrab,rdp.prof,p6,kontr,rdp.dsp,rdp.dsr,rdp.dtroch,rdp.cext,rdp.indsol, rdp.vitae,rdp.allerg,rdp.ishod));		
        else
    	sb4.append(String.format(";%d;%d;%d;%d;%d;%d;%s;%s;%s;%d;%s;%d;%d;%d;;%d;%s;%s;%td.%18$tm.%18$tY;%d;%d;%d;%d;%d;%d;%s;%s;;;%d;",rdp.shet,rdp.kolrod,rdp.abort,rdp.polj,rdp.vozmen,rdp.prmen,rdp.fiootec,rdp.mrabotec,rdp.telotec,grot,rdp.photec,hsm,hal,hdr,rdp.vozotec,rdp.mrab,rdp.prof,p6,kontr,rdp.dsp,rdp.dsr,rdp.dtroch,rdp.cext,rdp.indsol, rdp.vitae,rdp.allerg,rdp.ishod));		
        if (rdp.datasn != 0)
        sb4.append(String.format("%td.%1$tm.%1$tY;%d;%d;%d;%d;",p5,pr,rdp.diag,rdp.cvera,ek));
        else
        sb4.append(String.format(";%d;%d;%d;%d;",pr,rdp.diag,rdp.cvera,ek));
        if (rdp.dataosl != 0)
        sb4.append(String.format("%td.%1$tm.%1$tY;%d",rdp.dataosl,ru));
        else
        sb4.append(String.format(";%d",ru));
        System.out.println(sb4);		
		//		Encoded.Base64(rdp.fam,35,fam);
		sb.append("<br>");
		sb.append(String.format("%d;%s;%s;%s;%td.%5$tm.%5$tY;%s %s;%d;%d;%d;%s;%s;%s;%s%s;%s;%d;%d;%d;%d;%d;%d;%s;%s;%s;%s;%s;%s;%s;%s", rdp.uid, rdp.fam, rdp.im, rdp.ot, p7,rdp.docser,rdp.docnum,rdp.terpr,rdp.oblpr,tawn,rdp.street,rdp.house,rdp.flat,rdp.poms_ser,rdp.poms_nom,rdp.dog,rdp.stat,rdp.lpup,rdp.terp,rdp.terpr,rdp.oblpr,ftawn,rdp.fstreet,rdp.fhouse,rdp.fflat,rdp.fstreet,rdp.fhouse,rdp.fflat,grk,rdp.rez));		
		System.out.println(sb);		
 		
        ves = rdp.vesd;
		if (rdp.rost !=0) {ves = rdp.vesd/rdp.rost/rdp.rost*10000;
		if (ves>= 36)kod8 = kod8 + 8;}
		if (rdp.vozmen >= 16) kod6 = kod6 + 16;
		if (rdp.prmen >=34) kod6 = kod6 + 32;
		if (rdp.vozmen >= 16){ if (rdp.prmen >= 34 ) kod6 = kod6 + 64;}
		if (rdp.polj <= 14) kod6 = kod6 + 128;
		if (rdp.abort >= 4) kod6 = kod6 + 256;
		if (rdp.kont) kod6 = kod6 + 1024;
		//Con_diagn.csv
			try (AutoCloseableResultSet acrs21 = sse.execPreparedQuery("select d.diag,c.dex,d.d_vz,d.xzab,d.disp,s.name,da.datad from p_diag d,n_c00 c,n_s00 s, p_diag_amb da  where d.diag = c.pcod and d.cdol_ot = s.pcod  and da.id = d.id_diag_amb and d.npasp=?",rdp.npasp)) {
				if (acrs21.getResultSet().next()){
					p9 = new Date(acrs21.getResultSet().getLong(7));
					p10 = new Date(acrs21.getResultSet().getLong(7));
					if (acrs21.getResultSet().getInt(5) == 1) disp1 = 0; else disp1 = 0;
					if (acrs21.getResultSet().getInt(4) == 1) hr = 1; else hr = 0;
					sb6.append(String.format("%d;%d;%d;%4$td.%4$tm.%4$tY;%s;%s;%7$td.%7$tm.%7$tY;%s;%d", j,rdp.npasp,rdp.npasp,p1,acrs21.getResultSet().getString(6),acrs21.getResultSet().getString(1),p2,acrs21.getResultSet().getString(6),disp1,hr));		
					System.out.println(sb6);		
					 	//				dex = dex + ' '+ acrs1.getResultSet().getString(1);	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex1")){ dex1 =(dex1 +' '+ acrs21.getResultSet().getString(1)).trim();
			k1 = k1+1; k2 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex2")) {dex2 =(dex2 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k5 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex3")) {dex3 =(dex3 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k4 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex4")) {dex4 =(dex4 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k6 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex5")) {dex5 =(dex5+' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k3 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex6")) {dex6 =(dex6+' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k7 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex7")) {dex7 =(dex7 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1;k8 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex9")) {dex9 =(dex9 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k9 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 5).equals("dex10")) {dex10 =(dex10 +' '+ acrs21.getResultSet().getString(1)).trim();	
			k1 = k1+1; k10 = 1;}	
			if (acrs21.getResultSet().getString(2).substring(0, 3).equals("dak")) dak =(dak +' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 5).equals("dsost")) dsost =(dsost +' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dosl")) dosl =(dosl +' '+ acrs21.getResultSet().getString(1)).trim();
				
				if (acrs21.getResultSet().getString(1).charAt(0) == 'N') kod2 = 1;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O21")) kod5 = kod5+1;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O44")) kod5 = kod5+2;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O45")) kod5 = kod5+2;			
				if (acrs21.getResultSet().getString(1).substring(0, 5).equals("O23.0")) kod5 = kod5+4;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O24")) kod5 = kod5+8;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O30")) kod5 = kod5+16;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O32")) kod5 = kod5+32;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O36.0")) kod5 = kod5+64;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O99.0")) kod5 = kod5+128;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O13")) kod5 = kod5+256;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O14")) kod5 = kod5+512;			
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O15")) kod5 = kod5+1024;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I11")) kod7 =  kod7 + 1;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I12")) kod7 =  kod7 + 2;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I50")) kod7 =  kod7 + 4;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I49")) kod7 =  kod7 + 8;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I34")) kod7 =  kod7 + 16;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I35")) kod7 =  kod7 + 32;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I80")) kod7 =  kod7 + 64;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N11")) kod7 =  kod7 + 128;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N03")) kod7 =  kod7 + 256;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N18")) kod7 =  kod7 + 512;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E10")) kod8 =  kod8+1;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E03")) kod8 =  kod8+2;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E04")) kod8 =  kod8+4;
				
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E27")) kod8 =  kod8+16;
				if (acrs21.getResultSet().getString(1).substring(0, 2).equals("D6")) kod8 =  kod8+32;
				if (acrs21.getResultSet().getString(1).substring(0, 2).equals("B1")) kod8 =  kod8+64;
				if (acrs21.getResultSet().getString(1).substring(0, 5).equals("K72.1")) kod8 =  kod8+128;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("J96")) kod8 =  kod8+256;
				if (acrs21.getResultSet().getString(1).charAt(0) == 'F') kod8 =  kod8+512;
				if (acrs21.getResultSet().getString(1).substring(0, 2).equals("A1")) kod8 =  kod8+1024;
				if (acrs21.getResultSet().getString(1).substring(0, 3).equals("B20")) kod8 =  kod8+2048;
				if (acrs21.getResultSet().getString(1).substring(0, 5).equals("M95.5")) kod8 =  kod8+4098;
				if (acrs21.getResultSet().getString(1).substring(0, 2).equals("M3")) kod8 =  kod8+8196;
				if (k1 >=3) kod6 = kod6+1;
				if ((k2+k3+k4+k5+k6+k7+k8+k9+k10)>=3) kod6 = kod6 + 2;
				if (k3>0) kod6 = kod6 + 4;
				if (k4>0) kod6 = kod6 + 8;
				if (kod2 == 1) kod6 = kod6 + 512;
				}
				} catch (SQLException e) {
				((SQLException) e.getCause()).printStackTrace();
				throw new KmiacServerException();
			}
			try (AutoCloseableResultSet acrs21 = sse.execPreparedQuery("SELECT d.dspos,c.dex from p_rd_din d,n_c00 c where c.dex is not null and d.npasp=?",rdp.npasp)) {
				if (acrs21.getResultSet().next()){
//					dex = dex + ' '+ acrs1.getResultSet().getString(0);	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex1")) 
				dex1 =(dex1 +' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex2")) 
				dex2 =(dex2 +' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex3")) 
				dex3 =(dex3 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex4")) 
				dex4 =(dex4 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex5")) 
				dex5 =(dex5 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex6")) 
				dex6 =(dex6 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex7")) 
				dex7 =(dex7 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dex9")) 
				dex9 =(dex9 +' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 5).equals("dex10")) 
				dex10 =(dex10 + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 3).equals("dak")) 
				dak =(dak + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 5).equals("dsost")) 
				dsost =(dsost + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(2).substring(0, 4).equals("dosl")) 
				dosl =(dosl + ' '+ acrs21.getResultSet().getString(1)).trim();	
			if (acrs21.getResultSet().getString(1).charAt(0) == 'N') kod2 = 1;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O21")) kod5 = kod5+1;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O44")) kod5 = kod5+2;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O45")) kod5 = kod5+2;			
			if (acrs21.getResultSet().getString(1).substring(0, 5).equals("O23.0")) kod5 = kod5+4;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O24")) kod5 = kod5+8;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O30")) kod5 = kod5+16;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O32")) kod5 = kod5+32;			
			if (acrs21.getResultSet().getString(1).substring(0, 5).equals("O36.0")) kod5 = kod5+64;			
			if (acrs21.getResultSet().getString(1).substring(0, 5).equals("O99.0")) kod5 = kod5+128;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O13")) kod5 = kod5+256;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O14")) kod5 = kod5+512;			
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("O15")) kod5 = kod5+1024;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I11")) kod7 =  kod7 + 1;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I12")) kod7 =  kod7 + 2;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I50")) kod7 =  kod7 + 4;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I49")) kod7 =  kod7 + 8;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I34")) kod7 =  kod7 + 16;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I35")) kod7 =  kod7 + 32;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("I80")) kod7 =  kod7 + 64;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N11")) kod7 =  kod7 + 128;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N03")) kod7 =  kod7 + 256;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("N18")) kod7 =  kod7 + 512;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E10")) kod8 =  kod8+1;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E03")) kod8 =  kod8+2;
			if (acrs21.getResultSet().getString(1).substring(0, 3).equals("E04")) kod8 =  kod8+4;
				}
			} catch (SQLException e) {
				((SQLException) e.getCause()).printStackTrace();
				throw new KmiacServerException();
			}
			dex = (dex1+' '+dex2+' '+dex3+' '+dex4+' '+dex5+ ' '+dex6+' '+dex7 +' '+dex9+' '+dex10).trim();
			sb3.append(String.format("%d;%d;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",j,rdp.npasp,dex1,dex2,dex3,dex4,dex5,dex6,dex7,dex9,dex10,dex,dak,dsost,dosl));	
			System.out.println(sb3);		
		    sb5.append(String.format("%d;%d;%d;%d;%d;",j,rdp.npasp,rdp.obr,rdp.sem,rdp.rost));
		    sb5.append(String.format("%.2f;", rdp.vesd));
		    sb5.append(String.format("%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;;", kod2,rdp.osoco,rdp.uslpr,kod5,kod6,kod7,kod8,rdp.osp,ball1,ball2,ball3,ball4));
		    sb5.append(String.format("%td.%1$tm.%1$tY;", p8));
			System.out.println(sb5);		
//			acrs21.close();
		}
		//Vizit.csv
		List<RdVizit> rdVizit = getRdVizit();
		ball1 = 0;ball2 = 0;ball3 = 0;
		ball4 = 0;
		for (RdVizit rvz : rdVizit) {
			System.out.println(rvz);		
			p1 = new Date(rvz.dv);
			if (rvz.npr != 0){
			try (AutoCloseableResultSet acrs21 = sse.execPreparedQuery("select cod_sp from n_s00  where pcod=?",rvz.npr)) {
				if (acrs21.getResultSet().next()){
				tawn = acrs21.getResultSet().getInt(1);
				}
				} catch (SQLException e) {
					((SQLException) e.getCause()).printStackTrace();
					throw new KmiacServerException();
				}}
			sb1.append(String.format("%d;%d;%td.%3$tm.%3$tY;%d;%s %s %s;%s;%d;%d;%d;%d", rvz.uid, rvz.npasp, p1, rvz.sp, rvz.famvr,rvz.imvr,rvz.otvr,rvz.diag,rvz.mso,rvz.rzp,rvz.aim,rvz.npr));		
					}
		System.out.println(sb1);		
		// Con_vizit.scv
		j = 0;
		List<RdConVizit> rdConVizit = getRdConVizit();
		for (RdConVizit rcv : rdConVizit) {
			System.out.println(rcv);		
			j = j+1;
			Integer ot = 0;
			if (rcv.oteki != 0 ) ot = 1;
			sb2.append(String.format("%d;%d;%d;%.2f;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;;;%d;%d;%d;%d;%d;%d;", j, rcv.uiv,rcv.npasp, rcv.ves, rcv.ned,rcv.hdm,rcv.spl,rcv.lcad,rcv.ldad,rcv.rcad,rcv.rdad,ball1,ball2,ball3,ball4,rcv.oj,rcv.chcc,rcv.polpl,rcv.predpl,rcv.serd,rcv.serd1,ot,rcv.oteki));		
//			sb2.append(String.format("%d;%d;%d;%.2f;", j, rcv.uiv,rcv.npasp, rcv.ves));		
		}
		System.out.println(sb2);		
		osw.write(sb.toString());
		return "c:\\patient.html";
	} /*catch (SQLException e) {
		((SQLException) e.getCause()).printStackTrace();
		throw new KmiacServerException();
	}*/ catch (IOException e) {
		e.printStackTrace();
		throw new KmiacServerException();
	} finally {
		if (acrs != null)
			acrs.close();
		if (acrs2 != null)
			acrs2.close();
	}
//		return null;
	}

	@Override
	public String getKovInfoPol(int cpodr, long dn, long dk)
			throws KmiacServerException, TException {
		// TODO Auto-generated method stub
		String sqldi;
		String sqlis;
		String sqllo;
		String sqlos;
		String sqlpa;
		String sqllgot;
		String path = null;
		int bufRead;
		byte[] buffer = new byte[8192];
		
		try (FileOutputStream fos = new FileOutputStream(path = File.createTempFile("kovInfoPol", ".zip").getAbsolutePath());
	 		ZipOutputStream zos = new ZipOutputStream(fos)) {
	
		sqllgot = "SELECT p.npasp::integer AS bn, l.lgot::integer AS kgl "+
		           "FROM p_kov l, patient p, n_lkn k  "+
                   "WHERE l.npasp=p.npasp AND l.lgot=k.pcod AND  k.c_kov>0  AND p.cpol_pr = ? " ;		
		try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqllgot,cpodr ) ;
				InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
			zos.putNextEntry(new ZipEntry("lgot.dbf"));
			while ((bufRead = dbfStr.read(buffer)) > 0)
				zos.write(buffer, 0, bufRead);
		} catch (SQLException e) {
	        log.log(Level.ERROR, "SQl Exception: ", e);
			throw new KmiacServerException();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

				
	
	sqlpa = "SELECT p.npasp::integer AS bn, p.fam::char(20) AS fam,p.im::char(15) AS im,p.ot::char(20) AS otch,  "+
            "(case when p.pol=1 then 'М' else 'Ж' end)::char(1) AS sex,p.datar AS dr, " +
			"(select get_status(p.sgrp))::integer AS stat,p.adp_ul::char(25) AS ul,p.adp_dom::char(5) AS nd,p.adp_kv::char(5) AS nk, "+
            "k.gr_kov:: char(1) AS kat, "+
            "(case when p.poms_strg>0 then (select get_str_org(p.poms_strg)) end) AS str_org, " +
			"(case when pa.ishod=2 then 7 else 6 end)::integer AS pud, " +
			"p.poms_ndog::char(11) AS sdog,p.datapr AS dpp,p.terp::integer AS ter,p.cpol_pr::integer AS lpu, "+
			"pa.dataot AS datot,p.ter_liv::integer AS nas "+			
	        "FROM p_kov l, patient p, n_lkn k, p_nambk pa  "+
            "WHERE l.npasp=p.npasp AND pa.npasp=p.npasp AND l.lgot=k.pcod AND  k.c_kov>0  AND p.cpol_pr = ?" ;		
	try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlpa,  cpodr) ;
			InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
		zos.putNextEntry(new ZipEntry("kontipa.dbf"));
		while ((bufRead = dbfStr.read(buffer)) > 0)
			zos.write(buffer, 0, bufRead);
	} catch (SQLException e) {
        log.log(Level.ERROR, "SQl Exception: ", e);
		throw new KmiacServerException();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	sqldi = "SELECT p.npasp::integer AS bn, d.diag::char(6) AS isd,  "+
            "(case when d.ppi=1 then '+'  end)::char(1) AS pp, " +
            "(case when d.ppi=2 then '-' end)::char(1) AS pp, " +
            "(case when d.prizb=1 then '1' else  ' ' end)::char(1) AS priz, " +
            "(case when d.prizi=1 then '1' else ' ' end)::char(1) AS prizi " +
			"FROM p_kov l, patient p, n_lkn k, p_diag d  "+
            "WHERE l.npasp=p.npasp AND d.npasp=p.npasp AND l.lgot=k.pcod AND  k.c_kov>0  AND p.cpol_pr = ? AND " +
	        "d.xzab=2 AND  substr(diag,1,1)<>'Z' "   ;		
	try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqldi, cpodr) ;
			InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
		zos.putNextEntry(new ZipEntry("kontidi.dbf"));
		while ((bufRead = dbfStr.read(buffer)) > 0)
			zos.write(buffer, 0, bufRead);
	} catch (SQLException e) {
        log.log(Level.ERROR, "SQl Exception: ", e);
		throw new KmiacServerException();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	sqlis = "SELECT p.npasp::integer AS bn, a.usl_kov::char(15) AS kissl,  "+
           "m.fdat AS dvi  FROM p_kov l, patient p, n_lkn k, p_mer m, n_abd a  "+
            " WHERE l.npasp=p.npasp AND m.npasp=p.npasp AND l.lgot=k.pcod AND  k.c_kov>0  AND p.cpol_pr = ? AND " +
	        " m.pmer=a.pcod AND ((m.dnl>? AND m.dnl<?) or ( m.fdat>? AND m.fdat<?)) "   ;
	
	try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlis,  cpodr,new Date(dn), new Date(dk), new Date(dn), new Date(dk)) ;
			InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
		zos.putNextEntry(new ZipEntry("kontiis.dbf"));
		while ((bufRead = dbfStr.read(buffer)) > 0)
			zos.write(buffer, 0, bufRead);
	} catch (SQLException e) {
        log.log(Level.ERROR, "SQl Exception: ", e);
		throw new KmiacServerException();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	sqllo = "SELECT p.npasp::integer AS bn, a.cod_kov::char(20) AS klo,  "+
	           "m.dnl AS dn, m.dkl AS dk, m.ter::integer AS ter,m.lpu::integer AS lpu   "+
				"FROM p_kov l, patient p, n_lkn k, p_mer m, n_abd a  "+
	            "WHERE l.npasp=p.npasp AND m.npasp=p.npasp AND l.lgot=k.pcod AND  a.cod_kov>0  AND p.cpol_pr = ?" +
		        "AND m.pmer=a.pcod AND ((m.dnl>? AND m.dnl<?) or ( m.fdat>? AND m.fdat<?)) "   ;
	try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqllo,  cpodr,new Date(dn), new Date(dk), new Date(dn), new Date(dk)) ;
			InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
		zos.putNextEntry(new ZipEntry("kontilo.dbf"));
		while ((bufRead = dbfStr.read(buffer)) > 0)
			zos.write(buffer, 0, bufRead);
	} catch (SQLException e) {
        log.log(Level.ERROR, "SQl Exception: ", e);
		throw new KmiacServerException();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	sqlos = "SELECT p.npasp::integer AS bn, (select get_kodsp(m.cod_sp))::integer AS kspec,  "+
	           "m.datap AS dvo "+
				"FROM p_kov l, patient p, n_lkn k, p_vizit_amb m,n_s00 s  "+
	            "WHERE l.npasp=p.npasp AND m.npasp=p.npasp AND l.lgot=k.pcod AND  k.c_kov>0  AND p.cpol_pr = ?" +
		        "AND (m.datap>? AND m.datap<?) AND m.cdol=s.pcod  AND s.cod_kov<>0"   ;
	try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlos,   cpodr,new Date(dn), new Date(dk)) ;
			InputStream dbfStr = new DbfMapper(acrs.getResultSet()).mapToStream()) {
		zos.putNextEntry(new ZipEntry("kontios.dbf"));
		while ((bufRead = dbfStr.read(buffer)) > 0)
			zos.write(buffer, 0, bufRead);
	} catch (SQLException e) {
        log.log(Level.ERROR, "SQl Exception: ", e);
		throw new KmiacServerException();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

		} 
		/*catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	return path;
	}

	@Override
	public String dataSelection(long dbegin, long dend, int porc, String cform, int cpodr, long dclose)
			throws KmiacServerException, TException {
		String servpath = "";
		if (cform.equals("F25")) servpath = f025Selection(dbegin, dend, porc, cpodr);
		if (cform.equals("F39")) servpath = f039Selection(dbegin, dend, porc, cpodr);
		return servpath;
	}

	private String f025Selection(long dbegin, long dend, int porc, int cpodr) throws KmiacServerException, TException {
		String fpath = "", sqlo = "", sBuf, sc, sc0;
		String[] mas = {"","",""};
		int ndok = 0, n = 0, j = 0, sdok = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    	try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
                fpath = File.createTempFile("p_"+String.valueOf(porc).trim(), ".txt").getAbsolutePath()), "Cp866")) {
    		StringBuilder sb = new StringBuilder();
    		sqlo = "SELECT o.id, o.npasp, o.cpol, o.datao, o.ishod, o.rezult, o.talon, o.cod_sp, o.cdol, o.cobr, "+ 
                   "p.npasp, p.fam,p.im,p.ot,p.datar,p.pol,p.sgrp,p.adm_gorod,p.adm_ul,p.adm_dom,p.adm_korp,p.adm_kv, "+
    			   "p.mrab, p.poms_ndog,p.poms_ser, p.poms_nom, p.tdoc,p.docser,p.docnum,p.ter_liv, d.diag, d.vid_tr, "+
                   "(select sel_kov(o.npasp)::char(30) AS kov), (select sel_perv(o.npasp,d.diag,?,?,o.cpol)::char(3) AS perv), "+
                   "(select sel_obr(o.cod_sp)::char(2) AS obr),(select sel_boll(o.id)::char(30) AS boll),"+
                   "(select sel_pos(o.id,d.diag)::char(25) AS kpos)"+
                   " from p_vizit o, patient p, p_diag_amb d where o.npasp = p.npasp and (o.datao >= ? and o.datao <= ?) and cpol = ?"+
    			   " and o.id=d.id_obr and d.predv=false and (o.ishod > 0 and d.diag<='T99.99' or d.diag>='Z00')";
    		try (AutoCloseableResultSet acrp = sse.execPreparedQuery(sqlo, new Date(dbegin), new Date(dend),new Date(dbegin),new Date(dend),cpodr)){
				ResultSet rs = acrp.getResultSet();
    			if (rs.next()) {
 //   				sBuf = "!.ABIJEMLM,CC2E.JOBLIB,P="+String.valueOf(porc).trim()+",K"+System.lineSeparator();
 //   				sBuf += "!.U025,koldokum,"+System.lineSeparator();
    				sBuf = "*"+Integer.toString(cpodr)+","+Integer.toString(porc)+",1"+System.lineSeparator();
    				sb.append(sBuf);
    				while (rs.next()) {
    					n = 0;
    					j = 0;
    			  // 		System.out.println(rs.getString("fam").trim()+";"+rs.getString("diag")+";"+rs.getString("kpos"));
    			   		if (rs.getString("kpos") != null) {
    					ndok=ndok+1;
    					sdok=sdok+1;
    					if (ndok > 99){
    						sBuf = System.lineSeparator();
    						sb.append(sBuf);
     						porc = porc+1;
    		   				sBuf = "*"+Integer.toString(cpodr)+","+Integer.toString(porc)+",1"+System.lineSeparator();
    		   			  	sb.append(sBuf);
    		   			  	ndok = 1;
    					}
    					sBuf=";";
    					if (ndok < 10) 
    						sBuf += "0"+Integer.toString(ndok);
    					else 
    						sBuf += Integer.toString(ndok);
    					if ((rs.getString("diag").trim().substring(0,1).equals("Z"))){
    						sc = "3";
    						sc0 = "2";
    					}
    						else {
    							sc = rs.getString("perv").substring(0,1);
    						    sc0 = "1";
    						}	
    					sBuf += ","+Integer.toString(cpodr)+","+sc;
    			   	//	if (rs.getString("kpos") != null) {
    					for (int i =0; i < rs.getString("kpos").trim().length(); i++) {
    						if (rs.getString("kpos").substring(i,i+1).equals(",") || rs.getString("kpos").trim().length()-1 == i) {
    							if (rs.getString("kpos").trim().length() - 1 > i )
    							mas[n] = rs.getString("kpos").substring(j,i);
    							else {
    								mas[n] = rs.getString("kpos").substring(j).trim();
        						//	System.out.println(rs.getString("kpos").substring(j));
    							}
        						n=n+1;
    							j=i+1;
    						} 
    					};
    			   		//};
    					sc = mas[0]+","+sdf.format(rs.getDate("datar"));
    					sBuf += ","+sc;
    					sBuf += ","+Integer.toString(rs.getInt("pol"));
    					sBuf += ","+rs.getString("diag").trim(); 
    					if (rs.getInt("vid_tr")>0)
    					sBuf += ","+Integer.toString(rs.getInt("vid_tr"));
    					else
    						sBuf += ",";
    		//	   		System.out.println(rs.getString("obr"));
    			   		if (rs.getString("obr") != null)
    					sBuf += ","+rs.getString("cdol").trim()+"-"+rs.getString("obr").trim()+","+mas[1];
    			   		else
    			   			sBuf += ","+rs.getString("cdol").trim()+"-,"+mas[1];
    		//	   		System.out.println(rs.getString("perv"));
    			   		if (rs.getString("perv").trim().length() > 2)
    					sBuf += ","+rs.getString("perv").substring(2).trim();
    			   		else
    			   			sBuf += ",";
    			   		if (rs.getString("boll").length() >0)
    					sBuf += ","+rs.getString("boll").trim();
    			   		else
    			   			sBuf += ",,,,,";
    					sBuf += ","+rs.getString("kov").trim();
    				    sBuf += ","+rs.getString("fam").trim()+" "+rs.getString("im").trim()+" "+rs.getString("ot").trim();
    				    if (rs.getString("adm_gorod").trim().equals("НОВОКУЗНЕЦК Г.")){
    				    	if (rs.getString("adm_korp") != null)
    				    	sBuf += ","+rs.getString("adm_ul").trim()+","+rs.getString("adm_dom").trim()+","+rs.getString("adm_korp").trim();
    				    	else
    				    		sBuf += ","+rs.getString("adm_ul").trim()+","+rs.getString("adm_dom").trim()+",";
    				    	if (rs.getString("adm_kv") != null)
    				    		sBuf += ","+rs.getString("adm_kv").trim();
    				    	else
    				    		sBuf += ",";
    				    } else
    				    	sBuf += ",("+rs.getString("adm_gorod").trim()+"),,,";
    				    sBuf += ","+Integer.toString(rs.getInt("sgrp"));
    				    if (rs.getInt("mrab") < 0)
    				    	sBuf += ",Л"+Integer.toString(rs.getInt("mrab")).substring(1, Integer.toString(rs.getInt("mrab")).length());
    				    else
    				    	sBuf += ","+Integer.toString(rs.getInt("mrab"));
    				    if (rs.getString("poms_ndog") != null)
				    	sBuf += ","+rs.getString("poms_ndog").toString();
				    	else
				    		sBuf += ",";
				    	sBuf += ","+Integer.toString(rs.getInt("ishod"));
				    	sBuf += ","+Integer.toString(rs.getInt("talon"));
				    	sBuf += ","+Integer.toString(rs.getInt("rezult"));
				    	sBuf += ","+mas[2]+","+sc0;
				    	if (rs.getString("poms_ser") != null)
				    	sBuf += ","+rs.getString("poms_ser").trim();
				    	else
				    		sBuf += ",";
				    	if (rs.getString("poms_nom") != null)
				    	sBuf += ","+rs.getString("poms_nom").trim();
				    	else
				    		sBuf += ",";
				    	if (rs.getInt("ter_liv") == 0) 
				    		sBuf += ","+Integer.toString(rs.getInt("tdoc"))+","+rs.getString("docser").trim()+","+rs.getString("docnum").trim(); 
				    	else   
				    		sBuf += ",,,";
				    	sBuf += ","+Integer.toString(rs.getInt("ter_liv"))+":"+System.lineSeparator();
    				    sb.append(sBuf);
    				//    System.out.println(sBuf);
    				}
    				};	
				} else {sBuf = "Отсутствует информация для выгрузки за заданный период с "+dbegin +" по "+dend;
					sb.append(sBuf);
				}
				sBuf = "!.ABIJEMLM,CC2E.JOBLIB,P="+String.valueOf(porc).trim()+",K"+System.lineSeparator();
				sBuf += "!.U025,"+String.valueOf(sdok).trim()+","+System.lineSeparator();
    			osw.write(sBuf);
				osw.write(sb.toString());	
				}catch (Exception e1) {
					e1.printStackTrace();
				}
    	
    	}catch (Exception e){
    		e.printStackTrace();
    	}
		return fpath;

   	}

	private String f039Selection(long dbegin, long dend, int porc, int cpodr) throws KmiacServerException, TException {
		String fpath = "", sqlo = "", sBuf, sc, sc0, kodvr;
		String[] mas = {"","",""};
		int ndok = 0, hp = 0, mp = 0, hd = 0, md = 0, hda = 0, mda = 0, hprf = 0, mprf = 0, hpr = 0, mpr = 0, kodspec = 0;
		int kodpol = cpodr;
		int p0 = 0, ps = 0, pi = 0, p0_0 = 0, p0_14 = 0, p15_17 = 0, p60 = 0;
		int pz0 = 0, pz0_0 = 0, pz0_14 = 0, pz15_17 = 0, pz60 = 0;
		int pp0 = 0, pps = 0, pd0 = 0, perv_p = 0, perv_d = 0;
		int pzd0 = 0, pzd0_0 = 0, pzd0_14 = 0, pzd15_17 = 0, pzd60 = 0;
		int pp0_14 = 0, pp0_0 = 0, pp15_17 = 0, pbud = 0, poms = 0, pdms = 0, pplat = 0;
		float h; 
		long d1, d2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		SimpleDateFormat sdfr = new SimpleDateFormat("yyMMdd");
    	try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
                fpath = File.createTempFile("p_"+String.valueOf(porc).trim(), ".txt").getAbsolutePath()), "Cp866")) {
    		StringBuilder sb = new StringBuilder();
    		sqlo = "SELECT distinct(t.pcod,t.cdol), "+ 
                   "v.fam, v.im, v.ot, v.pol, v.datar ,v.obr from s_tabel t, s_vrach v "+
                   "where t.pcod = v.pcod and (t.datav >= ? and v.datav <= ?) and t.cpodr = ? "+
                   "order by t.pcod, t.cdol";
    		try (AutoCloseableResultSet acrp = sse.execPreparedQuery(sqlo, new Date(dbegin), new Date(dend),kodpol)){
				ResultSet rs = acrp.getResultSet();
    			if (rs.next()) {
    				while (rs.next()) {
    				ndok = ndok+1;
    				if (ndok > 99){
						sBuf = System.lineSeparator();
						sb.append(sBuf);
 						porc = porc+1;
		   				sBuf = "*"+Integer.toString(cpodr)+","+Integer.toString(porc)+",1"+System.lineSeparator();
		   			  	sb.append(sBuf);
		   			  	ndok = 1;
					}
					sBuf=";";
					if (ndok < 10) 
						sBuf += "0"+Integer.toString(ndok);
					else 
						sBuf += Integer.toString(ndok);
    	    		kodspec = rs.getInt("pcod");
    	    		kodvr = rs.getString("kodvr");
    				sBuf += Integer.toString(kodpol).trim()+","+rs.getString("fam").trim()+","+rs.getString("im").trim()+","+
    						rs.getString("ot").trim()+","+Integer.toString(rs.getInt("pol")).trim()+","+sdfr.format(rs.getDate("datar")).trim()+","+
    						rs.getString("im").substring(0,1)+rs.getString("ot").substring(0,1)+","+kodvr+","+
    						rs.getString("obr")+","+sdf.format(dend);
    				
    				sqlo = "SELECT timep, timed, timeda, timeprf, timepr "+ 
    	                    "from s_tabel "+
    	                    "where pcod = ? and cdol = ? and (t.datav >= ? and v.datav <= ?) and t.cpodr = ? "+
    	                    "order by t.pcod, t.cdol";
    				try (AutoCloseableResultSet acrs = sse.execPreparedQuery(sqlo, kodspec, kodvr, new Date(dbegin), new Date(dend), kodpol)){
    					ResultSet rst = acrs.getResultSet();
    				   	hp = 0; hd = 0; hda = 0; hprf = 0; hpr = 0;
    				   	mp = 0; md = 0; mda = 0; mprf = 0; mpr = 0;
        				while (rst.next()) {
        					hp = hp+(int)rst.getFloat("timep");
        					mp = mp+ (int)((rst.getFloat("timep")-(int)rst.getFloat("timep"))*100);
        					hd = hd+(int)rst.getFloat("timed");
        					md = md+ (int)((rst.getFloat("timed")-(int)rst.getFloat("timed"))*100);
        					hda = hda+(int)rst.getFloat("timeda");
        					mda = mda+ (int)(mp+(rst.getFloat("timeda")-(int)rst.getFloat("timeda"))*100);
        					hprf = hprf+(int)rst.getFloat("timeprf");
        					mprf = mprf+ (int)((rst.getFloat("timeprf")-(int)rst.getFloat("timeprf"))*100);
        					hpr = hpr+(int)rst.getFloat("timepr");
        					mpr = mpr+ (int)((rst.getFloat("timepr")-(int)rst.getFloat("timepr"))*100);
        				}
    				h = (hp+hd+hda+hprf+hpr+(mp+md+mda+mprf+mpr)/60);
    				sBuf += ","+String.valueOf(h).trim();
    				h = (hp+mp/60);
    				sBuf += ","+String.valueOf(h);
    				h = (hprf+mprf/60);
    				sBuf += ","+String.valueOf(h);
    				h = (hd+hda+(md+mda)/60);
    				sBuf += ","+String.valueOf(h);
    				}
    				sqlo = "SELECT distinct(v.npasp,v.datap), v.diag, v.mobs, v.cpos, v.opl, v.id_obr, "+ 
    						"p.datar, p.ter_liv,(select sel_xzab(v.npasp,v.diag)::int(1) AS xzab), "+
    						"(select sel_diag_god(v.npasp,v.diag,?,?)::int(1) AS perv"+
    	                    "from p_vizit_amb v, patient p, "+
    	                    "where v.npasp = p.npasp and (v.datap >= ? and v.datap <= ?) and v.cpol = ? and v.cod_sp = ? and v.cdol = ?";
    				try (AutoCloseableResultSet acrv = sse.execPreparedQuery(sqlo, new Date(dbegin), new Date(dbegin), new Date(dend), kodpol,kodspec,kodvr)){
    				  	ResultSet rsv = acrv.getResultSet();
    					while (rsv.next()) {
    			   		p0 = p0+1;
    			   		if (rsv.getInt("ter_liv")==22) ps = ps+1;
    			   		if (rsv.getInt("ter_liv")!=10) pi = pi+1;
    					d1 = rsv.getDate("datap").getTime();
    					d2 = rsv.getDate("datar").getTime() ;
    					if (rsv.getInt("mobs") == 2 || rsv.getInt("mobs") == 3) pd0 =pd0 + 1;
    					if (rsv.getInt("opl") == 1) pbud = pbud+1;
    					if (rsv.getInt("opl") == 2) poms = poms+1;
    					if (rsv.getInt("opl") == 3) pdms = pdms+1;
    					if (rsv.getInt("opl") == 4) pplat = pplat+1;
    					h = (float) ((d1-d2)/(3600000*24*365.25));
    					if (h < 1) p0_0 = p0_0 +1;
    					if (h < 15) p0_14 = p0_14+1;
    					if (h >= 15 && h < 18) p15_17 = p15_17+1;
    					if (h >= 60) p60 = p60+1;
    					if (rsv.getString("diag").substring(0, 1).equals("Z")){
    						pp0 = pp0+1;
    						if (rsv.getInt("cpos")==2) pps = pps+1;
    						if (h < 1) pp0_0 = pp0_0 +1;
        					if (h < 15) pp0_14 = pp0_14+1;
        					if (h >= 15 && h < 18) pp15_17 = pp15_17+1;
        	 			} else {
    						pz0 = pz0+1;
    						if (h < 1) pz0_0 = pz0_0 +1;
        					if (h < 15) pz0_14 = pz0_14+1;
        					if (h >= 15 && h < 18) pz15_17 = pz15_17+1;
    						if (h >= 60) pz60 = pz60+1;
    						if (rsv.getInt("perv") == 1 || rsv.getInt("xzab") == 1){
    							if (rsv.getInt("mobs") == 1) perv_p = perv_p+1;
    							if (rsv.getInt("mobs") == 2 || rsv.getInt("mobs") == 3) perv_d = perv_d+1;
    						}
    						if (rsv.getInt("mobs") == 2 || rsv.getInt("mobs") == 3) {
    							pzd0 = pzd0 + 1;
        						if (h < 1) pzd0_0 = pzd0_0 +1;
            					if (h < 15) pzd0_14 = pzd0_14+1;
            					if (h >= 15 && h < 18) pzd15_17 = pzd15_17+1;
            					if (h >= 60) pzd60 = pzd60 +1;
    						}
    			        	
    					}
    					
    					}
    					sBuf += ","+String.valueOf(p0).trim()+","+String.valueOf(ps).trim()+
    							","+String.valueOf(pi).trim()+","+String.valueOf(p0_14).trim()+
    							","+String.valueOf(p0_0).trim()+","+String.valueOf(p15_17).trim()+
    							","+String.valueOf(p60).trim()+","+String.valueOf(pz0).trim()+
    							","+String.valueOf(pz0_14).trim()+","+String.valueOf(pz0_0).trim()+
    							","+String.valueOf(pz15_17).trim()+","+String.valueOf(pz60).trim()+
    							","+String.valueOf(pp0).trim()+","+String.valueOf(pps).trim()+
    							","+String.valueOf(pd0).trim()+","+String.valueOf(pzd0).trim()+
    							","+String.valueOf(pzd0_14).trim()+","+String.valueOf(pzd0_0).trim()+
    							","+String.valueOf(pzd15_17).trim()+","+String.valueOf(pzd60).trim()+
    							","+String.valueOf(pp0_14).trim()+","+String.valueOf(pp0_0).trim()+
    							","+String.valueOf(pp15_17).trim()+","+String.valueOf(pbud).trim()+
    							","+String.valueOf(poms).trim()+","+String.valueOf(pdms).trim()+
    							","+String.valueOf(pplat).trim()+","+String.valueOf(perv_p).trim()+
    							","+String.valueOf(perv_d).trim();
    				
    				}
    				}
    			}
    			else {sBuf = "Отсутствует информация для выгрузки за заданный период с "+dbegin +" по "+dend;
				sb.append(sBuf);
			}
    		}
						 
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    				
    				
				sBuf = "!.ABBJEMLM,CC2M.JOBLIB,P=2319,K"+System.lineSeparator();
				sBuf += "!.U025,393,"+System.lineSeparator();
//				osw.write(sBuf);
//				osw.write(sb.toString());	
  	

		return fpath;
	}		

}
	
