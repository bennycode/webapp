#!/bin/bash


# default to RDS env vars
# otherwise use docker --env vars
DB_HOSTNAME=${RDS_HOSTNAME:-$DB_HOSTNAME}
DB_PORT=${RDS_PORT:-$DB_PORT}
DB_USERNAME=${RDS_USERNAME:-$DB_USERNAME}
DB_PASSWORD=${RDS_PASSWORD:-$DB_PASSWORD}
DB_SCHEMA=${RDS_DB:-$DB_SCHEMA}

# fallback to sensible defaults if nothing is set
export DB_HOSTNAME=${DB_HOSTNAME:-localhost}
export DB_PORT=${DB_PORT:-3306}
export DB_USERNAME=${DB_USERNAME:-root}
export DB_PASSWORD=${DB_PASSWORD:-root}
export DB_SCHEMA=${DB_SCHEMA:-welovecoding}

export DB_TYPE=${DB_TYPE:-mysql}
export DB_URL=jdbc:${DB_TYPE}://${DB_HOSTNAME}:${DB_PORT}/${DB_SCHEMA}
export DB_DRIVER=${DB_DRIVER:-com.mysql.jdbc.jdbc2.optional.MysqlDataSource}

java \
-Djava.security.egd=file:/dev/./urandom \
-Dspring.datasource.url=${DB_URL} \
-Dspring.datasource.driver-class-name=${DB_DRIVER} \
-Dspring.datasource.username=${DB_USERNAME} \
-Dspring.datasource.password=${DB_PASSWORD} \
-jar /data/ROOT.war