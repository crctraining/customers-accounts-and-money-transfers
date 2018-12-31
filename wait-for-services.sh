#! /bin/bash
set +e

done=false

host=$1
shift
path=$1
shift
ports=$*

if [ -z "$ports" ] ; then
    echo usage host /path port...
    exit -1
fi

echo host=$host path=$path ports=$ports

while [[ "$done" = false ]]; do
        for port in $ports; do
                curl --fail http://${host}:${port}${path} >& /dev/null
                if [[ "$?" -eq "0" ]]; then
                        done=true
                else
                        done=false
			break
                fi
        done
        if [[ "$done" = true ]]; then
                echo $path is available
                break;
  fi
  echo -n .
  sleep 1
done

set -e
