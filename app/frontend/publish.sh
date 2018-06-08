#!/bin/bash
BUILD_NUMBER=$1
TAGVERSION="1.0-b$BUILD_NUMBER"
echo $TAGVERSION
docker login -u admin -p admin123 localhost:5000
docker push localhost:5000/frontend:$TAGVERSION
