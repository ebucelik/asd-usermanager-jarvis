package at.ac.fhcampuswien.jarvis.service;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountServiceImplTest {

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository.save(new Account("Max", "Mustermann", "max", "Max123"));
    }

    @Test
    @DisplayName("createAccount")
    void createAccount() {
        // Arrange
        Account expectedAccount = new Account("Max", "Mustermann", "max", "Max123");

        // Act
        Account account = accountRepository.save(expectedAccount);

        // Assert
        assertEquals(expectedAccount.getUsername(), account.getUsername());
    }

    @Test
    @DisplayName("deleteAccount")
    void deleteAccount() {
        // Arrange
        Optional<Account> accountToDelete = accountRepository.findAccountByUsername("max");

        // Act
        accountRepository.delete(accountToDelete.get());
        Optional<Account> deletedAccount = accountRepository.findAccountByUsername(accountToDelete.get().getUsername());

        // Assert
        assertTrue(deletedAccount.isEmpty());
    }

    @Test
    @DisplayName("checkAccountByUsername")
    void checkAccountByUsername() {
        // Arrange
        Account expectedAccount = new Account("Max", "Mustermann", "max", "Max123");

        // Act
        boolean accountExists = accountRepository.findAccountByUsername(expectedAccount.getUsername()).isPresent();

        // Assert
        assertTrue(accountExists);
    }

    @Test
    @DisplayName("findAccountByUsername")
    void findAccountByUsername() {
        // Arrange
        Account expectedAccount = new Account("Max", "Mustermann", "max", "Max123");

        // Act
        Optional<Account> account = accountRepository.findAccountByUsername(expectedAccount.getUsername());

        // Assert
        assertEquals(expectedAccount.getFirstname(), account.get().getFirstname());
        assertEquals(expectedAccount.getLastname(), account.get().getLastname());
        assertEquals(expectedAccount.getUsername(), account.get().getUsername());
    }
}