FROM openjdk:8-jre
ADD ./target/Fileparser-0.0.1-SNAPSHOT.jar /usr/app1/Fileparser-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "Fileparser-0.0.1-SNAPSHOT.jar"]
