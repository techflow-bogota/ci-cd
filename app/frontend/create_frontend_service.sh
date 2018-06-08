#!/bin/bash
docker service rm frontend 2>/dev/null
BUILD_NUMBER=0

TAGVERSION="1.0-b$BUILD_NUMBER"
#docker swarm init
#docker network create -d overlay --attachable infrastructure_default
docker login -u admin -p admin123 localhost:5000
docker service create --name=frontend -p 3000:3000 --network=app --replicas=2 localhost:5000/frontend:$TAGVERSION
