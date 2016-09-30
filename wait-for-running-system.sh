#! /bin/bash -e

./wait-for-services.sh ${DOCKER_HOST_IP?} /health 8080 8081 8082 8083 8084

echo health checks succeeded. waiting for Eureka registration

for service in CUSTOMER-SERVICE ACCOUNT-SERVICE MONEY-TRANSFER-SERVICE CUSTOMER-VIEW-SERVICE; do
    ./wait-for-services.sh ${DOCKER_HOST_IP?} /eureka/apps/$service 8761
done