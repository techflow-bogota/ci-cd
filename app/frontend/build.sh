#!/bin/bash
#BUILD_NUMBER=1
BUILD_NUMBER="$1"
TAGVERSION="1.0-b$BUILD_NUMBER"
echo "Building the front end image for the following tag: "$TAGVERSION
#docker rmi -f $(docker images "localhost:5000/*"  | awk '{print $3}')
docker build -t localhost:5000/frontend:$TAGVERSION .
