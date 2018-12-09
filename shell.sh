#! /bin/bash
	apt-get update
	apt-get install -y libltdl7
	sudo rm -rf challenges/$1/$2/target/
	cp challenges/empty-0.0.1-SNAPSHOT.jar challenges/$1/$2 |& tee output5.txt
        cp challenges/Dockerfile challenges/$1/$2 |& tee output.txt
        javac challenges/WriteFile.java |& tee output1.txt
	java -cp "challenges:." WriteFile $1 $2 $3 |& tee output2.txt
	docker-compose up --build -d |& tee output3.txt
	docker cp challenges/$1/$2/ $1-$2:/usr/app/
	docker exec -t $1-$2 mvn clean compile package -DskipTests -f $2/pom.xml |& tee output4.txt
	docker-compose down -f
	docker rmi $(docker images -aq) -f

