# https://spring.io/guides/gs/spring-boot-docker/
FROM frolvlad/alpine-oraclejdk8:slim

#VOLUME /tmp

ADD webapp.war /data/ROOT.war
RUN chmod +x /data/ROOT.war

# A RUN command to "touch" the jar file so that it has a file modification time 
# (Docker creates all container files in an "unmodified" state by default). 
# Any static content (e.g. "index.html") would require the file to have a modification time.
RUN sh -c 'touch /data/ROOT.war'

EXPOSE 8080
ENTRYPOINT ["java",\
  "-Djava.security.egd=file:/dev/./urandom",\
  "-Dspring.datasource.url=jdbc:mysql://$RDS_HOSTNAME:$RDS_PORT/welovecoding",\
  "-Dspring.datasource.driver-class-name=com.mysql.jdbc.jdbc2.optional.MysqlDataSource",\
  "-Dspring.datasource.username=$RDS_USERNAME",\
  "-Dspring.datasource.password=$RDS_PASSWORD",\
  "-jar", "/data/ROOT.war"]