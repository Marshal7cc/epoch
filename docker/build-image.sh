#!/usr/bin/env bash

SOURCE_PATH=/usr/local/dev/docker/volumes/jenkins_home/workspace

# epoch-gateway
cd $SOURCE_PATH/epoch/epoch-infra/epoch-gateway
docker build -t epoch-gateway .

# epoch-admin
cd $SOURCE_PATH/epoch/epoch-monitor/epoch-admin
docker build -t epoch-admin .

# epoch-auth-center
cd $SOURCE_PATH/epoch/epoch-security/epoch-auth-center
docker build -t epoch-auth-center .

# epoch-system
cd $SOURCE_PATH/epoch/epoch-service/epoch-system
docker build -t epoch-system .

# epoch-hr
cd $SOURCE_PATH/epoch/epoch-service/epoch-hr
docker build -t epoch-hr .

# epoch-generator
cd $SOURCE_PATH/epoch/epoch-service/epoch-generator
docker build -t epoch-generator .
