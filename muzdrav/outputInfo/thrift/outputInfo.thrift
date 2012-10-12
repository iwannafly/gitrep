namespace java ru.nkz.ivcgzo.thriftOutputInfo

include "../../../common/thrift/kmiacServer.thrift"

struct InputAuthInfo {
	1: optional i32 userId;
	2: optional string cpodr_name;
	3: optional string clpu_name;
}

struct InputSvodVed {
    1: string dateb;
    2: string datef;
    3: i32 vozcat;
}

struct OutputSvodVed {
	1: optional i32 kodVidIssl;
	2: optional i32 userId;
	3: optional i32 npasp;
	4: optional string kodMetod;
	5: optional list<string> pokaz;
	6: optional string mesto;
	7: optional string kab;
	8: optional i32 pvizitId;
	9: optional string cpodr_name;
	10: optional string clpu_name;
}

struct OutputTest {
    1: optional string namebz;
    2: optional string diagsrpt;
}

struct VrachInfo {
	1: i32 pcod;
	2: string fam;
	3: string im;
	4: string ot;
	5: string cdol;
} 

struct VrachTabel {
	1: i32 pcod;
	2: string cdol;
	3: i64 datav;
	4: double timep;
	5: double timed;
	6: double timepda;
	7: double timeprf;
	8: double timepr;
	9: i32 nuch1;
	10: i32 nuch2;
	11: i32 nuch3;
}


struct InputPlanDisp {
    1: i32 kpolik;
    2: string namepol;
    3: string daten;
    4: string datek;
    5: optional string uchas;
}

struct OutputPlanDisp {
	1: string dateb;
	2: string datef;
	3: optional string namepol;
	4: optional string uchas;
   	5: optional string nambk;
	6: optional string fio;
	7: optional i64 datar;
	8: optional string adres;
	9: optional string kab;
	10: optional string diag;
	11: optional string name;
	12: optional i64 pdat;
	13: optional i32 nuch1;
	14: optional i32 d_grup;
}


service ThriftOutputInfo extends kmiacServer.KmiacServer {

   // string printSvodVed(1: OutputSvodVed osv) throws (1: kmiacServer.KmiacServerException kse);
    
    string printPlanDisp(1:InputPlanDisp ipd 2:OutputPlanDisp opd ) throws (1: kmiacServer.KmiacServerException kse);


    string printSvodVed(1: InputAuthInfo iaf 2: InputSvodVed isv 3: OutputSvodVed osv) throws (1: kmiacServer.KmiacServerException kse);

    string printTest(1: OutputTest ot 2: InputAuthInfo iaf ) throws (1: kmiacServer.KmiacServerException kse);

}
