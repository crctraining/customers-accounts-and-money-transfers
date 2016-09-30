#! /bin/bash -e

if [ -z "$*" ] ; then
 GRADLE_ARGS=assemble
 DOCKER_COMPOSE_ARGS=
else
 GRADLE_ARGS=
 DOCKER_COMPOSE_ARGS=

 for service in $* ; do
    GRADLE_ARGS="$GRADLE_ARGS :${service}:assemble"
    DOCKER_COMPOSE_ARGS="$DOCKER_COMPOSE_ARGS ${service//-/}"
 done

fi

echo GRADLE_ARGS $GRADLE_ARGS
echo DOCKER_COMPOSE_ARGS $DOCKER_COMPOSE_ARGS

./gradlew $GRADLE_ARGS -x :end-to-end-tests:test
docker-compose  build $DOCKER_COMPOSE_ARGS
docker-compose  up -d $DOCKER_COMPOSE_ARGS


