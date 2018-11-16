FROM openjdk:10-jre
ADD ./target/UserProfile-0.0.1-SNAPSHOT.jar /usr/app2/UserProfile-0.0.1-SNAPSHOT.jar
WORKDIR usr/app2
ENTRYPOINT ["java","-jar", "UserProfile-0.0.1-SNAPSHOT.jar"]
