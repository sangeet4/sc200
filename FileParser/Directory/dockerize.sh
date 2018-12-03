#! /bin/bash
	apt-get update
	apt-get install -y libltdl7
	cp challenges/empty-0.0.1-SNAPSHOT.jar challenges/$1/$2 |& tee output5.txt
	cp challenges/Dockerfile challenges/$1/$2 |& tee output.txt
        javac challenges/WriteFile.java |& tee output1.txt
	java -cp "challenges:." WriteFile $1 $2 $3 |& tee output2.txt
	docker-compose up --build $1-$2 |& tee output3.txt & 
	sleep 5
	docker cp challenges/$1/$2/ $1-$2:/usr/app/
	docker exec -t $1-$2 mvn clean test -f $2/pom.xml surefire-report:report |& tee output4.txt     

