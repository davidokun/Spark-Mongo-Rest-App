FROM david/centos7-base-java-8-server

MAINTAINER David Cuellar, davidokun99@gmail.com

# Add mongo repo configuration
ADD mongodb.repo  /etc/yum.repos.d/

# Update yum repositories
RUN yum update -y

# Install mongo db
RUN yum install -y mongodb-org

# Create default storage folder
RUN mkdir -p /data/db
