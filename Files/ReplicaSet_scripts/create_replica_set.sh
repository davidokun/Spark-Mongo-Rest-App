#!/usr/bin/bash

mkdir -p /data/rs1 /data/rs2 /data/rs3
mongod --replSet m101 --logpath "1.log" --dbpath /data/rs1 --port 27018 --oplogSize 64 --fork --smallfiles
mongod --replSet m101 --logpath "2.log" --dbpath /data/rs2 --port 27019 --oplogSize 64 --smallfiles --fork
mongod --replSet m101 --logpath "3.log" --dbpath /data/rs3 --port 27020 --oplogSize 64 --smallfiles --fork



config = { _id: "m101", members:[
          { _id : 0, host : "localhost:27018", priority:0, slaveDelay:5},
          { _id : 1, host : "localhost:27019"},
          { _id : 2, host : "localhost:27020"} ]
};

rs.initiate(config);
rs.status();
