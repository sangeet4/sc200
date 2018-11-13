    #! /bin/bash
	javac $1/$2.java
	output=$(java -cp "$1:." $2)	
	echo $output
