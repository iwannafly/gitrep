RMDIR /S /Q gen-java
"..\..\..\..\bin\thrift.exe" --gen java "auth.thrift"
"..\..\..\..\bin\thrift.exe" --gen java "..\..\..\common\thrift\kmiacServer.thrift"
"..\..\..\..\bin\thrift.exe" --gen java "..\..\..\common\thrift\fileTransfer.thrift"
"..\..\..\..\bin\thrift.exe" --gen java "..\..\..\common\thrift\libraryUpdater.thrift"
pause
