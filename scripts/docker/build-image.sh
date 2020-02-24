#!/usr/bin/env bash

SOURCE_PATH=/usr/local/dev/docker/volumes/jenkins_home/workspace

# epoch-gateway
cd $SOURCE_PATH/epoch/epoch-base/epoch-gateway
docker build -t epoch-gateway .

# epoch-monitor-admin
cd $SOURCE_PATH/epoch/epoch-monitor/epoch-monitor-admin
docker build -t epoch-monitor-admin .

# epoch-auth-oauth2
cd $SOURCE_PATH/epoch/epoch-security/epoch-auth-oauth2
docker build -t epoch-auth-oauth2 .

# epoch-system
cd $SOURCE_PATH/epoch/epoch-server/epoch-system
docker build -t epoch-system .

# epoch-HR
cd $SOURCE_PATH/epoch/epoch-server/epoch-HR
docker build -t epoch-HR .

# epoch-generator
cd $SOURCE_PATH/epoch/epoch-server/epoch-generator
docker build -t epoch-generator .
