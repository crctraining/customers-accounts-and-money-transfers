#! /bin/bash

HOST=${1?}

open http://${HOST?}:8761/
open http://${HOST}:8081/swagger-ui.html
open http://${HOST}:8082/swagger-ui.html
open http://${HOST}:8083/swagger-ui.html
open http://${HOST}:8084/swagger-ui.html
