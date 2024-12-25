package com.microservices_5in1.microservices_5in1.service;

import com.microservices_5in1.microservices_5in1.dto.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOne(int id);

    User save(User user);

    String deleteAUser(int id);
}
