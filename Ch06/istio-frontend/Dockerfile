FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/app.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
