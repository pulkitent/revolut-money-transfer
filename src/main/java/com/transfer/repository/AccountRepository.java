package com.transfer.repository;

import com.transfer.model.Account;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "UPDATE Account SET balance = ?2 WHERE account_id = ?1", nativeQuery = true)
    public void updateBalance(final String accountId, final BigDecimal finalBalance);
}
