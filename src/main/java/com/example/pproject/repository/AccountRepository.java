package com.example.pproject.repository;

import com.example.pproject.domain.Account;
import com.example.pproject.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account save(Account account){
        jdbcTemplate.update(
                "INSERT INTO account (id, password) VALUES(?, ?)",
                account.getId(), account.getPassword()
        );

        return findById(account.getId());
    }

    public Account findById(String accountId){
        List<Account> results = jdbcTemplate.query(
                "select * from account WHERE id = ?",
                new AccountRepository.AccountRowMapper(),
                accountId);

        return results.isEmpty() ? null : results.get(0);
    }

    public List<Account> findAll(){
        List<Account> results = jdbcTemplate.query(
                "select * from member",
                new AccountRepository.AccountRowMapper());

        return results;
    }

    public Account update(Account account){
        jdbcTemplate.update(
                "UPDATE account SET password = ? WHERE account_id = ?",
                account.getPassword());

        return findById(account.getId());
    }

/*    public void delete(){
        jdbcTemplate.update("TRUNCATE member");
    }*/

    public void clear(){
        jdbcTemplate.update("DELETE FROM account");
    }

    static class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account(
                    rs.getString("id"),
                    rs.getString("password")
            );

            return account;
        }
    }
}
