package com.semicolon.model.service.userService;

import com.semicolon.model.data.model.User;
import com.semicolon.model.data.repository.UserRepository;
import com.semicolon.model.web.exceptions.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        if(user == null){
            throw new NullPointerException("User object cant be null");
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws UserDoesNotExistException {

        User saveUser = userRepository.findById(user.getId()).orElse(null);
        if (saveUser == null) throw new UserDoesNotExistException();
        else{
            if(user.getFirstName() != null){
                saveUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null){
                saveUser.setLastName(user.getLastName());
            }
            if(user.getEmail() != null){
                saveUser.setEmail(user.getEmail());
            }
            if(user.getUsername() != null){
                saveUser.setUsername(user.getUsername());
            }
            if(user.getPassword() != null){
                saveUser.setPassword(user.getPassword());
            }
        }
        return userRepository.save(saveUser);
    }

    @Override
    public User findUserById(Integer id) throws UserDoesNotExistException {

        User saveUser = userRepository.findById(id).orElse(null);

        // check that user exist
        if(saveUser != null){
            return saveUser;

        }
        else{
            throw new UserDoesNotExistException();
        }
//        return null;
    }

    @Override
    public User findByUsername(String username) throws UserDoesNotExistException {
        User saveUser = userRepository.findByUsername(username);

        if(saveUser != null){
            return saveUser;
        }
        else{
            throw new UserDoesNotExistException();
        }
    }

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Integer id) throws UserDoesNotExistException {

        try {
            userRepository.deleteById(id);
            }catch (Exception exception){
            throw new UserDoesNotExistException();
        }



    }
}
