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
    6: i32 clpu;
}



service ThriftOutputInfo extends kmiacServer.KmiacServer {

   // string printSvodVed(1: OutputSvodVed osv) throws (1: kmiacServer.KmiacServerException kse);
    
    string printPlanDisp(1:InputPlanDisp ipd) throws (1: kmiacServer.KmiacServerException kse);

    string printNoVipPlanDisp(1:InputPlanDisp ipd) throws (1: kmiacServer.KmiacServerException kse);

    string printSvedDispObs(1:InputPlanDisp ipd) throws (1: kmiacServer.KmiacServerException kse);

    string printSvodVed(1: InputAuthInfo iaf 2: InputSvodVed isv) throws (1: kmiacServer.KmiacServerException kse);
}
