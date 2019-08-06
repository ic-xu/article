package com.common.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestUser {

    private String id;
    private String userName;
    private String userSex;
    private String identity;
    private String phone;
    private String email;
    private String address;
    private String joinTime;
    private boolean status;

}
