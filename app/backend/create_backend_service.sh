#!/bin/bash
#run this after backend
docker service rm backend 2>/dev/null
BUILD_NUMBER=0
TAGVERSION="1.0-b$BUILD_NUMBER"
docker login -u admin -p admin123 localhost:5000
docker service create --name=backend --network=app -p 8082:8080 --replicas=2 localhost:5000/backend:$TAGVERSION
