FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Juan Manuel Caicedo
VOLUME /tmp
ADD micro-parameter-service-*.jar app.jar
EXPOSE 9001
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]
