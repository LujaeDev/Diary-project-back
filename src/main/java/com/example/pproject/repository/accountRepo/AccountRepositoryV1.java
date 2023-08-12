package com.example.pproject.repository.accountRepo;

import com.example.pproject.domain.Account;
import com.example.pproject.repository.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryV1 {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepositoryV1(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account save(Account account){
        String sql = "INSERT INTO account (email, password, member_id) VALUES(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            //자동 증가 키
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"account_id"});
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getPassword());
            ps.setLong(3, account.getMemberId());

            return ps;
        }, keyHolder);

        Number keyValue = keyHolder.getKey();
        account.setAccountId(keyValue.longValue());
        return account;
    }

    public Optional<Account> findById(Long accountId){
        List<Account> results = jdbcTemplate.query(
                "select * from account WHERE id = ?",
                new AccountRepositoryV1.AccountRowMapper(),
                accountId);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<Account> findByEmail(String email){
        List<Account> results = jdbcTemplate.query(
                "select * from account WHERE email = ?",
                new AccountRepositoryV1.AccountRowMapper(),
                email);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }



    public List<Account> findAll(){
        List<Account> results = jdbcTemplate.query(
                "select * from member",
                new AccountRepositoryV1.AccountRowMapper());

        return results;
    }

    public Account update(Long accountId, AccountDto dto){
        jdbcTemplate.update(
                "UPDATE account SET email = ?, password = ?, member_id = ? WHERE account_id = ?",
                dto.getEmail(), dto.getPassword(), dto.getMemberId());

        return findById(accountId).get();
    }

    public void clear(){
        jdbcTemplate.update("DELETE FROM account");
    }

    static class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account(
                    rs.getLong("account_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getLong("member_id")
            );

            return account;
        }
    }
}
