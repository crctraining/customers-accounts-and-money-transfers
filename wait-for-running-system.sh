#! /bin/bash -e

./wait-for-services.sh ${DOCKER_HOST_IP?} /actuator/health 8080 8081 8082 8083 8084 8085


