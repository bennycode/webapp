# https://spring.io/guides/gs/spring-boot-docker/
FROM frolvlad/alpine-oraclejdk8:slim

ADD target/webapp.war /data/ROOT.war
RUN chmod +x /data/ROOT.war

# A RUN command to "touch" the jar file so that it has a file modification time 
# (Docker creates all container files in an "unmodified" state by default). 
# Any static content (e.g. "index.html") would require the file to have a modification time.
RUN sh -c 'touch /data/ROOT.war'
ADD run.sh run.sh
RUN chmod +x run.sh

EXPOSE 8080
CMD sh run.sh