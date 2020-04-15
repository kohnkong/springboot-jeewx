#!/bin/bash

docker-compose -f jeewx.yml stop

docker-compose -f jeewx.yml rm --force

exist=`docker inspect --format '{{.State.Running}}' jeewx`

if [ "${exist}" != "true" ];
then
        ./delete_image.sh
        echo "---->删除无用镜像"
fi

./build.sh jeewx

docker-compose -f jeewx.yml up -d