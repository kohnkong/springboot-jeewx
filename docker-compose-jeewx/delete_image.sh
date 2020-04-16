echo "----------start delete none image----------"
docker rmi $(docker images | grep "none" | awk '{print $3}')
echo "----------delete none image end----------"

echo "----------start rmi org.jeewxframework.boot/jeewx-boot-start:latest image----------"
docker rmi org.jeewxframework.boot/jeewx-boot-start:latest
echo "----------delete org.jeewxframework.boot/jeewx-boot-start:latest image end----------"
