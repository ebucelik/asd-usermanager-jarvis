package at.ac.fhcampuswien.jarvis.service;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
