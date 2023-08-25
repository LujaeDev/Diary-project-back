package com.example.pproject.member.dto.response;

import com.example.pproject.habit.application.HabitService;
import com.example.pproject.habit.domain.Habit;
import com.example.pproject.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConverterMemberResponse {
    private final HabitService habitService;


    public MemberResponse makeMemberResponse(Member member) {
        final Long memberId = member.getMemberId();

        List<Habit> positiveHabits = habitService.getPositiveHabitsByMemberId(memberId);
        List<Habit> negativeHabits = habitService.getNegativeHabitsByMemberId(memberId);

        MemberResponse mr = MemberResponse.builder()
                .memberId(member.getMemberId())
                .displayName(member.getFirstName() + member.getLastName())
                .profileContent(member.getProfileIContent())
                .positiveHabits(positiveHabits)
                .negativeHabits(negativeHabits)
                .build();

        return mr;
    }


}
