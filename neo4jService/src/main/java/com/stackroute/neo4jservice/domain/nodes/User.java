package com.stackroute.neo4jservice.domain.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity
public class User{

    @Id
    private String id;
    @Property
    private String name;
    @Property
    private String preferredLang;

    public User(String id,String name, String preferredLang) {
        this.id=id;
        this.name = name;
        this.preferredLang = preferredLang;
    }
    @JsonIgnore
    @Relationship(type="isPostedBy")
    Challenge challenge;
    @JsonIgnore
    @Relationship(type="isAttemptedBy")
    Challenge challengeA;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", preferredLang='" + preferredLang + '\'' +
                ", challengeA=" + challengeA +
                '}';
    }
}
