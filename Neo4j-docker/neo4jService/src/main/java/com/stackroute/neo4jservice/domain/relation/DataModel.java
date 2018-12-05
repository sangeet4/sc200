package com.stackroute.neo4jservice.domain.relation;


import com.stackroute.neo4jservice.domain.nodes.Challenge;
import com.stackroute.neo4jservice.domain.nodes.Concept;
import com.stackroute.neo4jservice.domain.nodes.Language;
import com.stackroute.neo4jservice.domain.nodes.User;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.StartNode;

public class DataModel {
    @Id
    private String id;
    @Property
    private String name;
    @Property
    private Language language;
    @Property
    private Concept concept;
    @Property
    private double level;
    @StartNode
    Challenge challenge;
    @EndNode
    User user;

    public DataModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", language=" + language +
                ", concept=" + concept +
                ", level=" + level +
                ", challenge=" + challenge +
                ", user=" + user +
                '}';
    }
}
