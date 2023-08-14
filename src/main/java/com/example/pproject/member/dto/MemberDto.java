package com.example.pproject.member.dto;
import lombok.Data;

@Data
public class MemberDto {
    private String firstName;
    private String lastName;

    public MemberDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
