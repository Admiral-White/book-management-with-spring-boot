package com.semicolon.model.service.userService;

import com.semicolon.model.data.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

//@Transactional(rollbackFor = Exception.class)
//public class UserDto {
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//    public String saveDto(UserDto userDto){
//        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//        return save (new User()).getId();
//    }
//}
