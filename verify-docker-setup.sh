#! /bin/bash

set -e

if [ -z "$*" ] ; then
    echo
    echo Note: if this fails with /bin/bash: /app/verify-docker-setup.sh: No such file or directory
    echo then you are not running in a directory that is shared with the Docker VM
    echo
    docker run --rm -v `pwd`:/app -e DOCKER_HOST_IP java:openjdk-8u91-jdk /bin/bash /app/verify-docker-setup.sh docker
else
    echo DOCKER_HOST_IP is ${DOCKER_HOST_IP?}
    echo
    echo checking reachability....
    ping -w 3 $DOCKER_HOST_IP
    echo
    echo Looks good
fi