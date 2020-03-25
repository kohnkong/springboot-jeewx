!/bin/bash
#查询所有java运行程序进程id
#ID=`ps -ef | grep java | grep -v "grep" | awk '{print $2}'`

#通过运行端口号查询进程id
ID=`ps -ef | grep java | grep 9000 | awk '{print $2}'`
echo $ID
echo "---------------"
for id in $ID
do
	kill -9 $id
	echo "killed $id"
done
source /etc/profile
nohup java -jar -Dserver.port=9000 jeewx-boot-start-1.0.0.jar >logger.txt 2>&1 &
echo "restart success"

