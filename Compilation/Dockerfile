FROM openjdk:8-jre
ADD ./target/Compilation-0.0.1-SNAPSHOT.jar /usr/app1/Compilation-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "Compilation-0.0.1-SNAPSHOT.jar"]
