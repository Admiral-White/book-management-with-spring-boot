package com.semicolon.model.web.controllers.user;

import com.semicolon.model.data.model.User;
import com.semicolon.model.data.repository.UserRepository;
import com.semicolon.model.service.userService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // log request body
        log.info("request object -->{}", user);

        // save request body
        try {
            userService.saveUser(user);
        }catch(NullPointerException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
