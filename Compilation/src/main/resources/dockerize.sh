#! /bin/bash
cd ..
docker-compose scale empty-container=1
docker exec -it empty-container bash
cd challenges/program
mvn clean package
cd ..
rm -rf program
cd ..
exit
docker-compose scale empty-container=0

