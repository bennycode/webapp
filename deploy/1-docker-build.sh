#!/bin/bash

cd ..
eval "$(docker-machine env default)"
docker build -t wlc-webapp .
