package at.ac.fhcampuswien.jarvis.service;

import at.ac.fhcampuswien.jarvis.models.Account;

import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);

    boolean checkAccountByUsername(String username);

    Optional<Account> findAccountByUsername(String username);
}
