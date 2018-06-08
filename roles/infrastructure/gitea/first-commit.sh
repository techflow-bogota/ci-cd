#!/bin/bash

sleep 10

# Frontend 
cd /vagrant/endava-techflow/app/frontend && git init
git config --global user.email "admin@cd.endava.net"
git config --global user.name "administrator"
git remote add origin http://administrator:admin123@localhost:3001/administrator/techflow-frontend.git
git add .
git commit -m "initial commit"
git push -u origin master

# Backend 
cd /vagrant/endava-techflow/app/backend && git init
git config --global user.email "admin@cd.endava.net"
git config --global user.name "administrator"
git remote add origin http://administrator:admin123@localhost:3001/administrator/techflow-backend.git
git add .
git commit -m "initial commit"
git push -u origin master