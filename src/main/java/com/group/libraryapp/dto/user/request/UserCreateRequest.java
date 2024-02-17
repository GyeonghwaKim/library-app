package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;

    //int는 null 안됨
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
