#! /bin/bash

services="customer-service account-group-service  account-service customer-view-service money-transfer-service eventuate-tram-cdc-service"

done=false

# ?(@.name =~ /.*-service/)

while [[ "$done" = false ]]; do
    for service in $services ; do
            ready=$(kubectl get po -l svc=$service -o "jsonpath={.items[0].status.containerStatuses[?(@.name == \"$service\" )].ready}")
            echo $service ready=$ready
            if [[ "$ready" = "true" ]]; then
                 echo $service is ready
                 done=true
            else
                 echo $service is NOT ready
                 done=false
                 break
            fi
    done
    if [[ "$done" = true ]]; then
            break;
  fi
  echo -n .
  sleep 1
done
