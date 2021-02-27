package com.semicolon.model.service.userService;

import com.semicolon.model.data.model.User;
import com.semicolon.model.data.repository.UserRepository;
import com.semicolon.model.web.exceptions.UserDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Autowired
    UserService userServiceImpl;

    User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
    }

    @Test
    void mockSaveUserToRepository() {
        when(userRepository.save(testUser)).thenReturn(testUser);
        userService.saveUser(testUser);

        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void mockUpdateUser() throws UserDoesNotExistException {
        when(userRepository.findById(2)).thenReturn(Optional.of(testUser));
//        userRepository.save(testUser);
        userService.findUserById(2);
//        userRepository.save(testUser);
        userService.saveUser(testUser);

        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void mockFindUserByIdTest() throws UserDoesNotExistException {
        when(userRepository.findById(2)).thenReturn(Optional.of(testUser));
        userService.findUserById(2);

        verify(userRepository, times(1)).findById(2);
    }

    @Test
    void mockFindByUsername() throws UserDoesNotExistException{
        when(userRepository.findByUsername("moseselite")).thenReturn(testUser);
        userService.findByUsername("moseselite");

        verify(userRepository, times(1)).findByUsername("moseselite");
    }

    @Test
    void mockFindAllUsers() {

        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        userService.findAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void mockDeleteUserById() throws UserDoesNotExistException {
        doNothing().when(userRepository).deleteById(2);
        userService.deleteUserById(2);

        verify(userRepository, times(1)).deleteById(2);
    }
}