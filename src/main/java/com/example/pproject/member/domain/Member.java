package com.example.pproject.member.domain;

import com.example.pproject.auth.dto.SignUpFormData;
import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Member extends BaseEntity {

    //계정 ID가 아닌 DB에서 PK로 설정할 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "profile_content")
    private String profileIContent;


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
