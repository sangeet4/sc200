package com.stackroute.neo4jservice.service.nodeservice;

import com.stackroute.neo4jservice.domain.nodes.User;
import com.stackroute.neo4jservice.repository.noderepository.UserRepository;
import com.stackroute.neo4jservice.service.nodeservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    @Override
    public User saveUser(User user)
        {
            User savedUser = userRepository.save(user);
            return savedUser;
        }

    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return ("Successfully deleted");
    }


    @Override
    public List<User> getAllUser() {
        return (List<User>)userRepository.findAll();
    }
}
