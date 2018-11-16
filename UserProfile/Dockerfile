FROM openjdk:8-jre
ADD ./target/UserProfile-0.0.1-SNAPSHOT.jar /usr/app1/UserProfile-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "UserProfile-0.0.1-SNAPSHOT.jar"]
