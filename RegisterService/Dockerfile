FROM openjdk:8-jre
ADD ./target/registerService-0.0.1-SNAPSHOT.jar /usr/app1/registerService-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "registerService-0.0.1-SNAPSHOT.jar"]
