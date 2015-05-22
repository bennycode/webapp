#!/bin/bash

#/usr/local/bin/mvn
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -Dwlc.database.driver.classname=<jdbc_driver> -Dwlc.database.url=<jdbc_address> -Dwlc.database.username=<db_username> -Dwlc.database.password=<db_password> -Popenshift -B -V

# Use war name 'ROOT.war' if you would like to deploy to the root path of your server
cp webapps/ROOT.war binary/dependencies/jbossews/webapps/<newname>.war
cp -r .openshift/* binary/repo/.openshift/

cd binary
tar -czvf ../webapps/binary.tar.gz ./
cd ..
rhc deploy -a <app_name> webapps/binary.tar.gz --no-force-clean-build --no-hot-deploy