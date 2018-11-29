#! /bin/bash
	echo $(cp challenges/Dockerfile challenges/$1/$2)
        echo $(javac challenges/writeToFile.java)
	echo $(java -cp "challenges:." writeToFile $1 $2 $3)
	echo $(docker-compose up --build $1-$2)

