package com.example.pproject.diary.domain;

import com.example.pproject.diary.dto.request.DiarySaveRequest;
import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private LocalDate date;

    public Diary() {

    }

    public Diary(DiarySaveRequest request, Long memberId) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.memberId = memberId;
        this.date = request.getDate();
    }
}
