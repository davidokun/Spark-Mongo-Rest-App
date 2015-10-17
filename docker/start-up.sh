#!/usr/bin/env bash
docker run -v /mnt/docker_share:/mnt/docker_share -d -p 27017:27017 -p 22:22 --name mongo-server-spark-mongo-app david/centos7-mongo-db-server

