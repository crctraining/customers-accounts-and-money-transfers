#! /bin/bash

docker run --rm --link bankingexample_mongodb_1:mongodb -i -t mongo:3.0.4 /usr/bin/mongo --host mongodb bankingexampledb
