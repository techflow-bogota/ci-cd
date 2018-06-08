#!/bin/bash
#BUILD_NUMBER=1
#build a runtime image
BUILD_NUMBER="$1"
TAGVERSION="1.0-b$BUILD_NUMBER"
echo "Building the backend image for the following tag: "$TAGVERSION
docker build -t localhost:5000/backend:$TAGVERSION .
