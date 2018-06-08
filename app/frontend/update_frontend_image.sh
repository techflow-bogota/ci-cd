#!/bin/bash
TAGVERSION="1.0-b"$1
echo "Deploying a rolling update with tag:" $TAGVERSION
docker login -u admin -p admin123 localhost:5000
docker service update --update-delay=30s --update-parallelism=1 --image=localhost:5000/frontend:$TAGVERSION frontend
