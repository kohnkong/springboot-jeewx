#FROM openjdk:8-jdk-alpine  使用openjdk打包出现servlet无法初始化问题
FROM java:8
VOLUME /tmp
ADD jeewx-boot-start-1.0.0.jar app.jar
EXPOSE 80
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
