#! /bin/bash 


if [ -z "$DOCKER_HOST_IP" ] ; then
    if [ -z "$DOCKER_HOST" ] ; then
      export DOCKER_HOST_IP=`hostname`
    else
      echo using ${DOCKER_HOST?}
      XX=${DOCKER_HOST%\:*}
      export DOCKER_HOST_IP=${XX#tcp\:\/\/}
    fi
fi

echo DOCKER_HOST_IP $DOCKER_HOST_IP

export SPRING_DATA_MONGODB_URI=mongodb://${DOCKER_HOST_IP}/bankingexampledb

echo SPRING_DATA_MONGODB_URI=$SPRING_DATA_MONGODB_URI
