namespace java ru.nkz.ivcgzo.thriftGenReestr

include "../../../common/thrift/kmiacServer.thrift"
include "../../../common/thrift/classifier.thrift"

	/**
	 * Ошибка формирования реестра
	 */
	exception ReestrNotFoundException {
	}

service ThriftGenReestr extends kmiacServer.KmiacServer {

	/**
        * Создает реестр
	*/
	void GenReestrFile(1:i32 cpodr, 2:i64 dn, 3:i64 dk, 4:i32 vidreestr) throws (1:ReestrNotFoundException rnfe);

	/*Классификаторы*/

	/**
	 * Классификатор поликлиник для текущего ЛПУ (N_N00 (pcod))
	 */
	list<classifier.IntegerClassifier> getAllPolForCurrentLpu(1:i32 lpuId) throws (1: kmiacServer.KmiacServerException kse);

	/**
	 * Классификатор поликлиники для текущего ЛПУ (N_N00 (pcod))
	 */
	list<classifier.IntegerClassifier> getPolForCurrentLpu(1:i32 polId) throws (1: kmiacServer.KmiacServerException kse);

	/**
	 * Классификатор отделений для текущего ЛПУ (N_O00 (pcod))
	 */
	list<classifier.IntegerClassifier> getOtdForCurrentLpu(1:i32 lpuId) throws (1: kmiacServer.KmiacServerException kse);

}