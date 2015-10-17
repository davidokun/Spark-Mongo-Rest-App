#!/usr/bin/env bash
cd base-java-server/ &&
sh build.sh &&
cd ../base-mongodb-server/ &&
sh build.sh &&
cd ../db-server/ &&
sh build.sh
