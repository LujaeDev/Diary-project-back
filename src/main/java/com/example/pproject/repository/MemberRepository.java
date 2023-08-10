package com.example.pproject.repository;

import com.example.pproject.domain.Member;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public MemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member save(Member member){
        jdbcTemplate.update(
                "INSERT INTO member (member_serial_number, name, phone_number) VALUES(?, ?, ?)",
                member.getMemberSN(), member.getName(), member.getPhoneNumber()
        );

        return findByMemberId(member.getMemberSN());
    }

    public Member findByMemberId(String memberId){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER WHERE member_serial_number = ?",
                new MemberRowMapper(),
                memberId);

        Member member1 = jdbcTemplate.queryForObject("select * from MEMBER WHERE member_serial_number = ?", (ResultSet rs, int numRow) -> {
            Member member = new Member(
                    rs.getString("member_serial_number"),
                    rs.getString("name"),
                    rs.getString("phone_number")
            );

            return member;
        }, memberId);

        return results.isEmpty() ? null : results.get(0);
    }

    public List<Member> findAll(){
        List<Member> results = jdbcTemplate.query(
                "select * from member",
                new MemberRowMapper());

        return results;
    }

    public Member update(Member member){
        jdbcTemplate.update(
                "UPDATE member SET name = ?, phone_number = ? WHERE member_serial_number = ?",
                member.getName(),
                member.getPhoneNumber(),
                member.getMemberSN());

        return findByMemberId(member.getMemberSN());
    }

/*    public void delete(){
        jdbcTemplate.update("TRUNCATE member");
    }*/

    public void clear(){
        jdbcTemplate.update("DELETE FROM member");
   }

    static class MemberRowMapper implements RowMapper<Member>{
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getString("member_serial_number"),
                    rs.getString("name"),
                    rs.getString("phone_number")
            );

            return member;
        }
    }
}
