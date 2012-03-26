namespace java ru.nkz.ivcgzo.thriftServerAuth

include "../../../common/thrift/fileTransfer.thrift"
include "../../../common/thrift/kmiacServer.thrift"

/**
 * Пользователя с таким логином и паролем не найдено.
 */
exception UserNotFoundException {
}

service ThriftServerAuth extends fileTransfer.FileTransfer {
	/**
	 * Процедура аутентификации.
	 */
	kmiacServer.UserAuthInfo auth(1: string login, 2: string password) throws (1: UserNotFoundException unf);
	
}
