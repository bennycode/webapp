#mvn -Pdev,production,standalone clean install /
#    -DskipTests=true /
#    -Dmaven.javadoc.skip=true /
#    -Dwlc.database.driver.classname=com.mysql.jdbc.Driver /
#    -Dwlc.database.url=$DB_URL /
#    -Dwlc.database.username=$DB_USERNAME /
#    -Dwlc.database.password=$DB_PASSWORD /
#    -B -V
mvn -Pdev,production,standalone clean install /
	-DskipTests=true /
    -Dmaven.javadoc.skip=true /
	-B -V
mvn -Pdev,production,standalone antrun:run@prepare-eb-bundle -DskipTests=true
mvn -Pdev,production,standalone assembly:assembly@package-eb-bundle -DskipTests=true