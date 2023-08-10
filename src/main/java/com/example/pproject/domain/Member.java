package com.example.pproject.domain;

import lombok.Data;

@Data
public class Member {
    private String memberSN;
    private String name;
    private String phoneNumber;

    public Member(){

    }

    public Member(String memberSN, String name, String phoneNumber) {
        this.memberSN = memberSN;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
