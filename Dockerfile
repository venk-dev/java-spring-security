FROM amazoncorretto:8u342
MAINTAINER Venkatesh K <venkkdev@gmail.com>

ENV  \
    SpringApp_HOME=/opt/executor \
    BASE_DIR=/executor-data \
    JVM_OPTS="-server -Xms1g -Xmx1g -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:+ScavengeBeforeFullGC -XX:+DisableExplicitGC"

VOLUME ${BASE_DIR}
USER root

RUN mkdir -p /app-root/runtime/data
RUN mkdir -p $SpringApp_HOME
COPY target/spring-security-docker-container-1.0-SNAPSHOT.jar $SpringApp_HOME/spring-security-docker-container-1.0-SNAPSHOT.jar

COPY --chown=root:root  ./docker-resource/docker-run.sh $SpringApp_HOME/

RUN chmod +x $SpringApp_HOME/docker-run.sh
#USER executor
CMD $SpringApp_HOME/docker-run.sh

EXPOSE 8080 8443