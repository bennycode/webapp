#!/bin/bash

mvn -Pproduction,standalone clean install \
	-DskipTests=true \
  -Dmaven.javadoc.skip=true \
	-B -V