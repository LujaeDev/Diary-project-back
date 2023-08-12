package com.example.pproject.domain;

import com.example.pproject.web.form.SignUpFormData;
import lombok.Data;

@Data
public class Member {
    //계정 ID가 아닌 DB에서 PK로 설정할 번호
    private Long memberId;
    private String firstName;
    private String lastName;


    public Member(){

    }

    public Member( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Member(Long memberId, String firstName, String lastName) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Member(SignUpFormData formData) {
        this.firstName = formData.getFirstName();
        this.lastName = formData.getLastName();
    }
}
