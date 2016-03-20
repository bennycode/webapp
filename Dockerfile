FROM java:jre
EXPOSE 8080
ADD target/webapp-0.0.1-SNAPSHOT.war /data/ROOT.war

CMD java -jar /data/ROOT.war
