package com.microservices_5in1.microservices_5in1.service;

import com.microservices_5in1.microservices_5in1.dto.User;
import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findOne(int id);

    UserEntity save(User user);

    String deleteAUser(int id);

    MappingJacksonValue findAllDynamicFiltering();
}
