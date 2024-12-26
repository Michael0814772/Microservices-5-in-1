package com.microservices_5in1.microservices_5in1.service;

import com.microservices_5in1.microservices_5in1.dto.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public interface UserService {

    List<User> findAll();

    EntityModel<User> findOne(int id);

    User save(User user);

    String deleteAUser(int id);

    MappingJacksonValue findAllDynamicFiltering();
}
