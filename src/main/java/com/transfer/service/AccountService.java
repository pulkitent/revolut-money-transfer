package com.transfer.service;

import com.transfer.model.Account;
import com.transfer.repository.AccountRepository;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    Account findById(final String id) {

        return accountRepository.findById(id).get();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    void transferMoney(final Account fromAccount, final Account toAccount, final BigDecimal amount) {

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.updateBalance(fromAccount.getId(), fromAccount.getBalance());
        accountRepository.updateBalance(toAccount.getId(), toAccount.getBalance());
    }
}
