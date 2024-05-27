package com.techrs.avante_rs.controller;

import com.techrs.avante_rs.dto.IdResponse;
import com.techrs.avante_rs.dto.user.CompleteUserResponse;
import com.techrs.avante_rs.dto.user.EditUserRequest;
import com.techrs.avante_rs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping
    public void deleteUserLogged(){
        userService.deleteLogged();
    }
    @GetMapping
    public CompleteUserResponse getUserLogged(){
        return userService.getLogged();
    }
    @PutMapping
    public IdResponse editUserLogged(@RequestBody @Valid EditUserRequest request){
        return userService.editUserLogged(request);
    }
}
