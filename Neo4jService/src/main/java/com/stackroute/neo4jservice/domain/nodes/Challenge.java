package com.stackroute.neo4jservice.domain.nodes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Challenge {

    @Property
    @Id
    private String id;
    @Property
    private String title;
    @Property
    private double level;
    @Property
    private String challengeStamp;

    @JsonIgnore
    @Relationship(type="isTypeOf")
    Concept concept;
    public Challenge() {

    }

    public Challenge(String id, String title, double level, String challengeStamp) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.challengeStamp = challengeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getchallengeStamp() {
        return challengeStamp;
    }

    public void setSmallDescription(String smallDescription) {
        this.challengeStamp = smallDescription;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", level=" + level +
                ", smallDescription='" + challengeStamp + '\'' +
                '}';
    }
}

