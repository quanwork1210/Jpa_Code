package com.jpa.jpaCode.Dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String alpha2Code;
    private String address;
}
