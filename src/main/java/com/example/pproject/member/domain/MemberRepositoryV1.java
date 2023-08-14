package com.example.pproject.member.domain;

import com.example.pproject.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//사용 안하는 클래스
public class MemberRepositoryV1 implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    public MemberRepositoryV1(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "INSERT INTO member (first_name, last_name) VALUES(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            //자동 증가 키
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"member_id"});
            ps.setString(1, member.getFirstName());
            ps.setString(2, member.getLastName());

            return ps;
        }, keyHolder);


        Number keyValue = keyHolder.getKey();
        member.setMemberId(keyValue.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long memberId){
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER WHERE member_id = ?",
                new MemberRowMapper(),
                memberId);

        Member member1 = jdbcTemplate.queryForObject("select * from MEMBER WHERE member_id = ?", (ResultSet rs, int numRow) -> {
            Member member = new Member(
                    rs.getLong("member_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );

            return member;
        }, memberId);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Member> findAll(){
        List<Member> results = jdbcTemplate.query(
                "select * from member",
                new MemberRowMapper());

        return results;
    }

//    @Override
//    public Member update(Long memberId, MemberDto dto){
//        jdbcTemplate.update(
//                "UPDATE member SET first_name = ?, last_name = ? WHERE member_id = ?",
//                dto.getFirstName(),
//                dto.getLastName(),
//                memberId);
//
//        return findById(memberId).get();
//    }

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
                    rs.getLong("member_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );

            return member;
        }
    }
}
