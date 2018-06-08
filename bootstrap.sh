#!/bin/bash
#packages required to conf mgmnt with ansible and to control docker 
apt-get -y update && apt-get install -y python python-pip 
#pip install docker-py 
pip install docker-compose