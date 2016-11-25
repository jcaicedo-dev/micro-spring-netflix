FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Juan Manuel Caicedo
VOLUME /tmp
ADD micro-discovery-server-*.jar app.jar
EXPOSE 9000
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
