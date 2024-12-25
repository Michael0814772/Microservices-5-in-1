package com.microservices_5in1.microservices_5in1.service;

import com.microservices_5in1.microservices_5in1.dto.user.User;
import com.microservices_5in1.microservices_5in1.exception.MyUserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }
    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        User user = users.stream().filter(predicate).findFirst().orElse(null);

        if (user == null) {
            String message = String.format("User " + id + " not found");
            throw new MyUserNotFoundException(message);
        }

        return user;
    }

    @Override
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    @Override
    public String deleteAUser(int id) {

        users.removeIf(deleteUser -> deleteUser.getId().equals(id));
        log.info("user remaining - {}", users);
        return String.format("Successfully deleted user %s", id);
    }
}
