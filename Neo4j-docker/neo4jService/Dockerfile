FROM openjdk:8-jre
ADD ./target/neo4jservice-0.0.1-SNAPSHOT.jar /usr/app1/neo4jservice-0.0.1-SNAPSHOT.jar
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "neo4jservice-0.0.1-SNAPSHOT.jar"]
