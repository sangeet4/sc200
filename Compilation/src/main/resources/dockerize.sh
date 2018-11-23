#! /bin/bash

ssh ubuntu@<private-ip> "docker-compose scale empty-container=1"
#ssh ubuntu@<private-ip> "docker exec -it empty-container bash"
ssh user@<service name> "cd challenges/program ; mvn clean package ; cd .. ; rm -rf program ; cd .. "
ssh ubuntu@<private-ip> "docker-compose scale empty-container=0"


