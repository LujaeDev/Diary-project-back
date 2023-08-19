package com.example.pproject.account.domain;

import com.example.pproject.account.exception.NoSuchAccountException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(final String email);

    default void validateExistsByEmail(final String email) {
        if (!existsByEmail(email)) {
            throw new NoSuchAccountException();
        }
    }
}
