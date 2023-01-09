package com.shop.springshop.controller;

import com.shop.springshop.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class TestController {

    @GetMapping(value = "/test")
    public UserDto test(){

        UserDto userDto = new UserDto();
        userDto.setAge(20);
        userDto.setName("hoon");

        return userDto;
    }
}
