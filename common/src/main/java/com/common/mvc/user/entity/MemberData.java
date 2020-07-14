package com.common.mvc.user.entity;

import lombok.Data;

import java.util.List;

@Data
public class MemberData {
    private String jop;
    private List<Member> membersData;
}
