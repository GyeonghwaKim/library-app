package com.group.libraryapp.controller;
//트랜잭션 : 다. 여러 SQL을 사용해야 할 때
//한 번에 성공시키거나, 하나라도 실패하면 모두 실패시키는 기능

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.UserServiceV1;

import com.group.libraryapp.service.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userServiceV2;

    public UserController(UserServiceV2 userServiceV2) {
        this.userServiceV2 = userServiceV2;

    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userServiceV2.saveUser(request);
    }


    @GetMapping("/user")
    public List<UserResponse> getUser(){
       return userServiceV2.getUser();
    }

    //update user
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userServiceV2.updateUser(request);

    }
//삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userServiceV2.deleteUser(name);
        }



    @GetMapping("/user/error-test")
    public void errorTest() {
        throw new IllegalArgumentException();
    }


}
