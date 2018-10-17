#! /bin/bash -e

for service in CUSTOMER-SERVICE ACCOUNT-SERVICE MONEY-TRANSFER-SERVICE CUSTOMER-VIEW-SERVICE ACCOUNT-GROUP-SERVICE; do
    ./wait-for-services.sh ${DOCKER_HOST_IP?} /eureka/apps/$service 8761
done