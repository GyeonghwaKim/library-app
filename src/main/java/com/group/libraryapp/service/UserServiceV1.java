package com.group.libraryapp.service;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.UserJdbcRepository;

import java.util.List;

//@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;
    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }
    //현재 유저가 있는지 없는지 등을 확인하고 예외처리
    public void updateUser(UserUpdateRequest request){
        //존재하지않음 = true
        if(userJdbcRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){
        //유저가 존재하지 않으면
        if (userJdbcRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUser(name);
    }

    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(),request.getAge());
    }

    public List<UserResponse> getUser() {
        return userJdbcRepository.getUserResponse();
    }
    }
