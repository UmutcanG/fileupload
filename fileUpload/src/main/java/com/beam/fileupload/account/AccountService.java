package com.beam.fileupload.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(Account account) {
        if (accountRepository.findByUserName(account.getUserName()) != null)
            throw new RuntimeException("already have this name");
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    account.setId(UUID.randomUUID().toString());
    accountRepository.save(account);
    }


    public Account login(Account account) {
        Account acc = accountRepository.findByUserName(account.getUserName());
        if (acc==null){
            return null;
        }
        if (passwordEncoder.matches(account.getPassword(), acc.getPassword())){
            return acc;
        }
        return null;
    }

    public List<Account> getUsers() {
        return accountRepository.findAll();
    }
}
