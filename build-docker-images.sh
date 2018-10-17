#! /bin/bash -e

./gradlew assemble

for service in customer-service account-service money-transfer-service customer-view-service  ; do
  (cd $service ; docker build -t $service .)
done
