#!/bin/bash

cd ..
HOST_IP=$(ifconfig en0 | awk '/ *inet /{print $2}')
echo "${HOST_IP}"
# docker run -e RDS_USERNAME=rds_username -e RDS_HOSTNAME=rds_hostname -p 8080:8080 -d wlc-webapp
docker run -e DB_HOSTNAME=${HOST_IP} -e DB_USERNAME=test -e DB_PASSWORD=root -p 8080:8080 -d wlc-webapp