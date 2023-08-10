package com.example.pproject.dao;

import com.example.pproject.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


//안씀
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
