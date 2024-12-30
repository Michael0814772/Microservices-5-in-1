package com.microservices_5in1.microservices_5in1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices_5in1.microservices_5in1.Utils.DynamicFiltering;
import com.microservices_5in1.microservices_5in1.dto.User;
import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import com.microservices_5in1.microservices_5in1.exception.MyUserNotFoundException;
import com.microservices_5in1.microservices_5in1.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final DynamicFiltering dynamicFiltering;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public List<UserEntity> findAll() {

        //        for (User user : users) {
//            UserEntity userEntity = new UserEntity();
//            userEntity.setName(user.getName());
//            userEntity.setBirthDate(user.getBirthDate());
//            userEntity = userEntityRepository.save(userEntity);
//            log.info("saved user entity - {}", userEntity);
//        }
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntity findOne(int id) {

        Optional<UserEntity> findById = userEntityRepository.findById(id);

        if (findById.isEmpty()) {
            String message = String.format("User " + id + " not found");
            throw new MyUserNotFoundException(message);
        }
//        Predicate<? super User> predicate = user -> user.getId().equals(id);

//        User user = users.stream().filter(predicate).findFirst().orElse(null);

//        if (user == null) {
//            String message = String.format("User " + id + " not found");
//            throw new MyUserNotFoundException(message);
//        }

        //        EntityModel<UserEntity> entityModel = EntityModel.of(user);

//        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(HelloWorldController.class).retrieveAUser(id));

//        log.info("link builder - {}", linkBuilder);

//        entityModel.add(linkBuilder.withRel("all-users"));

        return findById.get();
    }

    @Override
    public UserEntity save(User user) {

        UserEntity userEntity = objectMapper.convertValue(user, UserEntity.class);
        log.info("user entity - {}", userEntity);
        userEntity = userEntityRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public String deleteAUser(int id) {

        int deleteCount = userEntityRepository.deleteCount(id);
        log.info("deleted count - {}", deleteCount);

        return deleteCount > 0 ? String.format("Successfully deleted user %s", id) : String.format("User %s not found", id);
    }

    @Override
    public MappingJacksonValue findAllDynamicFiltering() {
        log.info("findAllDynamicFiltering");

        return dynamicFiltering.seanBeanFiltering(userEntityRepository.findAll(), "birthDate", "name");
    }
}
