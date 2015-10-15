#!/usr/bin/env bash
cd 1-base-java-server/ &&
sh build.sh &&
cd ../2-base-mongodb-server/ &&
sh build.sh &&
cd ../3-db-server/ &&
sh build.sh
