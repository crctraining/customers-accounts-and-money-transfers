#! /bin/bash -e

SERVICE_HOST_IP=$DOCKER_HOST_IP ./gradlew -a :end-to-end-tests:cleanTest :end-to-end-tests:test
