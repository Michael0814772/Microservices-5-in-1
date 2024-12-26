package com.microservices_5in1.microservices_5in1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices_5in1.microservices_5in1.Utils.DynamicFiltering;
import com.microservices_5in1.microservices_5in1.controller.HelloWorldController;
import com.microservices_5in1.microservices_5in1.dto.User;
import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import com.microservices_5in1.microservices_5in1.exception.MyUserNotFoundException;
import com.microservices_5in1.microservices_5in1.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final DynamicFiltering dynamicFiltering;
    private final ObjectMapper objectMapper;

    private static final List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }

    @Transactional
    @Override
    public List<User> findAll() {

        for (User user : users) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(user.getName());
            userEntity.setBirthDate(user.getBirthDate());
            userEntity = userEntityRepository.save(userEntity);
            log.info("saved user entity - {}", userEntity);
        }
        return users;
    }

    @Override
    public EntityModel<User> findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        User user = users.stream().filter(predicate).findFirst().orElse(null);

        if (user == null) {
            String message = String.format("User " + id + " not found");
            throw new MyUserNotFoundException(message);
        }

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(HelloWorldController.class).retrieveAUser(id));

        log.info("link builder - {}", linkBuilder);

        entityModel.add(linkBuilder.withRel("all-users"));

        return entityModel;
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

        users.removeIf(deleteUser -> deleteUser.getId().equals(id));
        log.info("user remaining - {}", users);
        return String.format("Successfully deleted user %s", id);
    }

    @Override
    public MappingJacksonValue findAllDynamicFiltering() {
        log.info("findAllDynamicFiltering");

        return dynamicFiltering.seanBeanFiltering(users, "birthDate", "name");
    }
}
