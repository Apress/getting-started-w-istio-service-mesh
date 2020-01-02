FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/app.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
