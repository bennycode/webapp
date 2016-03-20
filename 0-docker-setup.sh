#!/bin/bash


# create default docker machine
# skip machine creation if already exists
$(/usr/local/bin/docker-machine active) > tmp.txt
COMMAND_OUTPUT=$(ls $HOME/.docker/machine/machines | wc -l | xargs)
echo "command output: $COMMAND_OUTPUT"
if [ "$COMMAND_OUTPUT" == "0" ]; then
	echo "creating default virtual box machine"
	docker-machine create --driver virtualbox default
else
	echo "skipping machine creation"
fi

# set docker machine env vars
eval "$(docker-machine env default)"

# print machine IP
echo "The following IP was assigned to your docker machine. Please use this IP to access your service."
docker-machine ip default
