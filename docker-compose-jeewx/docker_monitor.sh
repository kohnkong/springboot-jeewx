#!/bin/bash
#监控容器的运行状态

#容器名称  传入参数
containerName=$1
#当前时间
now=$(date +"%Y-%m-%d %H:%M:%S")

# 查看进程是否存在
exist=$(docker inspect --format '{{.State.Running}}' ${containerName})

if [ "${exist}" != "true" ]; then
      docker start ${containerName}
      #记录日志
      echo "${now} 重启docker容器，容器名称：${containerName}" >>/opt/docker_log/docker_monitor.log
fi

EOF
