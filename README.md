## Build

[![Build Status](https://travis-ci.org/welovecoding/webapp.svg?branch=master)](https://travis-ci.org/welovecoding/webapp)

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

## Deployment

- http://localhost:8080/
