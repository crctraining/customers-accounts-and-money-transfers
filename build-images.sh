#! /bin/bash -e

./gradlew build -x :end-to-end-tests:test
docker-compose -f docker-compose-infrastructure.yml -f docker-compose.yml -f docker-compose-eureka.yml build
