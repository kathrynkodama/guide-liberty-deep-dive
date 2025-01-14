#!/bin/bash

if [[ -e ./start/inventory ]]; then
    rm -fr ./start/inventory
fi
mkdir ./start/inventory
cp -fr ./finish/module-jwt/* ./start/inventory
mkdir -p ./start/inventory/src/main/liberty/config/resources/security
cp ./finish/system/src/main/liberty/config/resources/security/key.p12 ./start/inventory/src/main/liberty/config/resources/security/key.p12
echo Now, you may run following commands to continue the class:
echo cd start/inventory
echo mvn liberty:dev -DserverStartTimeout=120
