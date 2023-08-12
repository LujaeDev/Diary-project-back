package com.example.pproject.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static com.example.pproject.connection.ConnectionConst.*;

@Configuration
public class DbConfig {
    @Bean
    public DataSource dataSource(){
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setMaximumPoolSize(10);

        return ds;
    }

    //MemberRepositroy가 사실상 memberDao의 역할을 하는거 아닌가?
//    @Bean
//    public MemberDao memberDao(){
//        return new MemberDao(dataSource());
//    }
}
