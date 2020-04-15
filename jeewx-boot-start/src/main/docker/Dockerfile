# 基础镜像，运行环境打包,（注：docker镜像出现无法初始化servlet,使用FROM java:8）
FROM openjdk:8-jre-alpine

# 指定系统环境变量或定义环境变量
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    KONGQI_SLEEP=0 \
    JAVA_OPTS=""

# 维护者
MAINTAINER Mr.Kong@by 1031652818@qq.com

#一个特别指定的目录，用于存储数据，该命令的作用是在/var/lib/docker创建一个名为tmp的目录，在开启redis服务时，需要特别指定redis的数据存储在哪个文件夹，此时这个命令就十分有用
VOLUME /tmp

# 添加用户
#RUN adduser -D -s /bin/sh kongqi

#WORKDIR /home/kongqi

#ADD  entrypoint.sh entrypoint.sh
#RUN chmod 755 entrypoint.sh && chown kongqi:kongqi entrypoint.sh
#RUN chmod -R +x /home/kongqi

#USER kongqi

#拷贝文件并且重命名
ARG ARTIFACT
ADD ${ARTIFACT} app.jar

#并不是真正的发布端口，这个只是容器部署人员与建立image的人员之间的交流，即建立image的人员告诉容器布署人员容器应该映射哪个端口给外界
EXPOSE 9000

#容器启动时运行的命令，相当于我们在命令行中输入java -jar xxxx.jar，为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT
#ENTRYPOINT ["java","-jar","/app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["./entrypoint.sh"]
