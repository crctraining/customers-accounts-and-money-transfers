#! /bin/bash

docker run --link $(echo ${PWD##*/} | sed -e 's/-//g')_mongodb_1:mongodb -i -t --rm mongo:3.0.4 sh -c "exec /usr/bin/mongo --host mongodb bankingexampledb"
