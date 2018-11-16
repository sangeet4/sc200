FROM openjdk:8
EXPOSE 8085
ADD target/neo4jservice-0.0.1-SNAPSHOT.jar /usr/src/neo4jservice-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","neo4jservice-0.0.1-SNAPSHOT.jar"]
