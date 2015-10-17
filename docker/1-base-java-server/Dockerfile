FROM centos:7

MAINTAINER David Cuellar, davidokun99@gmail.com

ENV JAVA_VERSION=jdk1.8.0_60
ENV JAVA_FILE_NAME=jdk-8u60-linux-x64.tar.gz
ENV JAVA_URL=http://download.oracle.com/otn-pub/java/jdk/8u60-b27/${JAVA_FILE_NAME}
ENV JAVA_HOME=/usr/local/${JAVA_VERSION}/
ENV MAVEN_VERSION=3.3.3

RUN yum update -y
RUN yum clean all
RUN yum -y install wget
RUN yum -y install unzip
RUN yum -y install tar
RUN yum -y install gettext
RUN yum -y install openssh-server
RUN yum -y install yum-utils
RUN yum -y install net-tools
RUN mkdir /var/run/sshd
RUN /usr/bin/ssh-keygen -A
RUN echo 'root:secret' | chpasswd
RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" ${JAVA_URL}
RUN tar xzf ${JAVA_FILE_NAME}
RUN rm ${JAVA_FILE_NAME}
RUN mv ${JAVA_VERSION} /usr/local/
ENV PATH "$PATH":${JAVA_HOME}/bin:.:
RUN ln -s /${JAVA_HOME}/bin/jjs /usr/bin/jjs

RUN wget http://www.eu.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN tar xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN rm apache-maven-${MAVEN_VERSION}-bin.tar.gz
RUN mv apache-maven-${MAVEN_VERSION} /opt/
ENV MAVEN_HOME=/opt/apache-maven-${MAVEN_VERSION}
ENV PATH "$PATH":${MAVEN_HOME}/bin:.:

