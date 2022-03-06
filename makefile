installJar:
	 mvn install:install-file -Dfile=E:/java_workspace/forward/smart_open_sdk-0.0.14.jar -DgroupId=sdk -DartifactId=smart_open_sdk -Dversion=0.0.14 -Dpackaging=jar
package:
	mvn package -Dmaven.test.skip=true