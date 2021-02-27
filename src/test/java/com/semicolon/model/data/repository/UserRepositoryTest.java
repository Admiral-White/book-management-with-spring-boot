package com.semicolon.model.data.repository;

import com.semicolon.model.data.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    User user = new User();

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
    }

    // Test that we can save user to the database (Create)
    @Test
    public void whenISaveUser(){

        // create a new user and save
        user.setFirstName("esther");
        user.setLastName("killer");
        user.setEmail("estherkiller@yahoo.com");
        user.setUsername("estherkill");
        user.setPassword("encoder456");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        log.info("user info before it is saved -->{}", user);


//        userRepository.save(user);

        user = userRepository.save(user);

        assertThat(user.getId()).isNotNull();
        log.info("user object after being saved -->{}", user);
    }

    @Test
    public void whenIUpdateUser(){
        user.setFirstName("Bill");
        user.setLastName("Clinton");
        user.setUsername("Billy2Clinton");
        user.setEmail("BillyWhite@gmail.com");
        user.setPassword("BuhariMustGoNow");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        log.info("this is the information after user being saved -->{}", user);

        user.setLastName("Inyang");

        log.info("This is the new user information after the update operation -->{}", user);

        // check to see if the update went through
        assertThat(user.getLastName()).isEqualTo("Inyang");


    }


    @Test
    @Transactional
    @Rollback(value=false)
    public void whenIDeleteUser(){
        log.info("fetching all the data before this operation -->");
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers){
            System.out.println(user);
        }

        // check if user exist
        boolean result = userRepository.existsById(2);
        log.info("result --> {}", result);

        // confirm that the information above is true (exist)
        assertThat(result).isTrue();

        // delete user
        userRepository.deleteById(2);

        result = userRepository.existsById(2);
        log.info("result after the delete operation -->{}", result);

        // check if user exists
        assertThat(result).isFalse();

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIFindAllUsers(){
        // find all users
        List<User> users = userRepository.findAll();

        log.info("get all the users from the database -->{}", users);

        // assert that user is not empty
        assertThat(users).isNotEmpty();

        // assert that table size on the database is the same
        assertThat(users.size()).isEqualTo(2);

    }

    @Test
    public void findUserByUsername(){
        User user = userRepository.findByUsername("moseselite");

        assertThat(user.getUsername()).isEqualTo("moseselite");

    }
}