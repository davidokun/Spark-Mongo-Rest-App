# Spark-Mongo-Rest-App
REST application using Spark Java framework, Freemaker and Mongo DB

### Content
  - Docker container build and run

### Docker container build and run

* First build the `1-base-java-server` image
go to `/docker/1-base-java-server` folder and run:
```sh
$ sh build.sh
```
* Then go to `/docker/2-base-mongodb-server` folder and run:
```sh
$ sh build.sh
```
* And for the final image go to `/docker/3-db-server` and run:
```sh
$ sh build.sh
```
* Finally run the `start-up.sh` script to create and start the container:
```sh
$ sh start-up.sh
```


