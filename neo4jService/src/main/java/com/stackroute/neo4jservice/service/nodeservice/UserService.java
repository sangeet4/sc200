package com.stackroute.neo4jservice.service.nodeservice;

import com.stackroute.neo4jservice.domain.nodes.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public String deleteUser(String id);
    public List<User> getAllUser();

}
