FROM openjdk:8-jre
ADD ./target/challenge-creator-0.0.1-SNAPSHOT.jar /usr/app1/challenge-creator-0.0.1-SNAPSHOT.jar
HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8180/actuator/health || exit 1
WORKDIR usr/app1
ENTRYPOINT ["java","-jar", "challenge-creator-0.0.1-SNAPSHOT.jar"]
