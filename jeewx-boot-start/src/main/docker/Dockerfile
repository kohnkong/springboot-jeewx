FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD jeewx-boot-start-1.0.0.jar app.jar
EXPOSE 8000
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
