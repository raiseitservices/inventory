echo off

set ENV=%1

IF [%1]==[] (
	echo “No args passed. Loading production software.”
	java -Xms128m -Xmx256m -Dspring.config.location=../config/application-prod.properties -jar ../lib/EaglesEye-0.0.1-SNAPSHOT.jar
) ELSE (
	echo “Starting debug version ”$1
	java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -server -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Xms128m -Xmx256m -Dspring.config.location=../config/application-prod.properties -jar ../lib/EaglesEye-0.0.1-SNAPSHOT.jar --debug
)
