FROM davidokun/base-java-server-spark-mongo-app

MAINTAINER David Cuellar, davidokun99@gmail.com

ADD mongodb.repo  /etc/yum.repos.d/

RUN yum install -y mongodb-org

RUN mkdir -p /data/db
