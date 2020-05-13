#!/usr/bin/env bash

SOURCE_PATH=/usr/local/dev/docker/volumes/jenkins_home/workspace

# epoch-gateway
cd $SOURCE_PATH/epoch/epoch-base/epoch-gateway
docker build -t epoch-gateway .

# epoch-admin
cd $SOURCE_PATH/epoch/epoch-monitor/epoch-admin
docker build -t epoch-admin .

# epoch-auth-server
cd $SOURCE_PATH/epoch/epoch-security/epoch-auth-server
docker build -t epoch-auth-server .

# epoch-system
cd $SOURCE_PATH/epoch/epoch-server/epoch-system
docker build -t epoch-system .

# epoch-HR
cd $SOURCE_PATH/epoch/epoch-server/epoch-HR
docker build -t epoch-hr .

# epoch-generator
cd $SOURCE_PATH/epoch/epoch-server/epoch-generator
docker build -t epoch-generator .
