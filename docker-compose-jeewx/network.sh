# 查看docker网络类型

docker network ls

#bridge：桥接网络
#默认情况下启动的Docker容器，都是使用 bridge，Docker安装时创建的桥接网络，每次Docker容器重启时，会按照顺序获取对应的IP地址，这个就导致重启下，Docker的IP地址就变了

#none：无指定网络
#使用 --network=none ，docker 容器就不会分配局域网的IP

#host： 主机网络
#使用 --network=host，此时，Docker 容器的网络会附属在主机上，两者是互通的。例如，在容器中运行一个Web服务，监听8080端口，则主机的8080端口就会自动映射到容器中。

# 创建自定义网络

docker network create --driver=bridge --subnet=172.18.0.0/16 mynetwork

# 创建doker容器

# 创建nginx使用自定义网络
docker run --name nginx -d \
-p 80:80 -p 443:443 \
--restart=always \
-v /data/nginx/cert:/etc/nginx/cert \
-v /data/nginx/html/:/usr/share/nginx/html \
-v /data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v /data/nginx/conf.d/:/etc/nginx/conf.d \
-v /data/nginx/logs/:/var/log/nginx/ \
--net mynetwork --ip 172.18.0.2 nginx
