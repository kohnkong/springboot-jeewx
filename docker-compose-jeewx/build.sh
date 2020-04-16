#!/bin/bash

echo "use grep -E specific project,enter name regex to args"
sp=".*";
if [ -z $1 ];
then
  echo "do all"
else
  sp=$1;
   echo "----------arg1 $sp ----------"
fi

#projectsDir=/data/project/misc/projects/;
#ls -F $projectsDir| grep '/$'|grep tx|grep -E $sp 
#for updatedir in $(ls -F $projectsDir| grep '/$'|grep tx|grep -E $sp) ;do cd $projectsDir;echo "updatedir--->"$updatedir;pwd;cd $updatedir; pwd;git pull;mvn clean install;if [ -z `echo $updatedir|grep common` ] ;then pwd ;webdir=`ls -d ./*|grep web`;echo $webdir;cd $webdir;mvn dockerfile:build; fi;done

workDir=/data/springboot-jeewx

echo "----------cd "$workDir ----------

cd $workDir;

git reset --hard && git pull

cd $workDir/docker-compose-jeewx

chmod +x chown.sh && ./chown.sh && cd $workDir

mvn clean install

cd $workDir/jeewx-boot-start

mvn docker:build;

echo "---------- dockerfile:build success -------------"
