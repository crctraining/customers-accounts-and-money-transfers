#! /bin/bash -e

docker-compose stop
docker-compose rm -v --force

. set-env.sh

docker-compose up -d mongodb

./gradlew build -x :end-to-end-tests:test

docker-compose build

docker-compose up -d

./wait-for-running-system.sh

export SERVICE_HOST_IP=${DOCKER_HOST_IP}

# Run them once because of the Hystrix timeout

./gradlew -a :end-to-end-tests:cleanTest :end-to-end-tests:test

# Run them again expecting success

./gradlew -a -P ignoreE2EFailures=false :end-to-end-tests:cleanTest :end-to-end-tests:test

docker-compose stop
docker-compose rm -v --force


