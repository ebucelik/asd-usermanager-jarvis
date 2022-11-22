package at.ac.fhcampuswien.jarvis.service;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean checkAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username).isPresent();
    }
}
