package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//저장
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save( new User(request.getName(),request.getAge()));

    }


    //조회
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

//수정
    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user =userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());

        userRepository.save(user);

    }

    //삭제
    @Transactional
    public void deleteUser(String name){

        User user=userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);

    }
}
