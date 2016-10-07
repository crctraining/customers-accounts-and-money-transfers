#! /bin/bash

docker run --rm --link $(echo ${PWD##*/} | sed -e 's/-//g')_mongodb_1:mongodb -i -t mongo:3.0.4 /usr/bin/mongo --host mongodb bankingexampledb
