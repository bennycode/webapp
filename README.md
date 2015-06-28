The OpenShift `jbossews` cartridge documentation can be found at:

- http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat

## Build app

```bash
npm install -g bower-installer
bower-installer
mvn package
```

## Run

```bash
# with spring-boot-maven-plugin
mvn spring-boot:run -Pdev

# as executable war
mvn clean package -Pdev
java -jar target/*.war

# create deployable war
mvn clean package -Pdeployable
```

## Environment

- [JBoss EWS 2.1.0](https://access.redhat.com/articles/111723) (Tomcat 7.0.54, OpenJDK 7)
