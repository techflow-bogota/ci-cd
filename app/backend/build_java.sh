#!/bin/bash
#BUILD_NUMBER=1
#build the artifacts inside a container

#clean java classes
rm -r target
mkdir target

#use maven to isolate dependencies
docker run --rm --name build -v "$(pwd)":/usr/src/backend -w /usr/src/backend maven:3.5.3-jdk-8 mvn clean package -DskipTests
