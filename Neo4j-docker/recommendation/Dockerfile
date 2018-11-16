FROM openjdk:8
ADD target/recommendation-0.0.1-SNAPSHOT.jar /usr/app1/recommendation-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar","recommendation-0.0.1-SNAPSHOT.jar"]
