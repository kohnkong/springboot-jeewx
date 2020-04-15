docker rmi $(docker images | grep "none" | awk '{print $3}')

docker rmi org.jeewxframework.boot/jeewx-boot-start:latest
