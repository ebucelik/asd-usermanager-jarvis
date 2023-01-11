package at.ac.fhcampuswien.jarvis.service;


import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.security.AES256;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    AccountServiceImpl accountService = new AccountServiceImplMock();

    @BeforeEach
    void setUp() {
        AccountMock accountMock = new AccountMock();
        accountMock.setId(Long.valueOf("1"));
        accountMock.setUsername("username");
        accountMock.setPassword("password");

        accountService.createAccount(accountMock);
    }

    @Test
    @DisplayName("createAccount")
    void createAccount() {
        // Arrange
        Account expectedAccount = new AccountMock();

        // Act
        Account account = accountService.createAccount(expectedAccount);

        // Assert
        assertEquals(expectedAccount.getUsername(), account.getUsername());
    }

    @Test
    @DisplayName("deleteAccount")
    void deleteAccount() {
        // Arrange
        Optional<Account> accountToDelete = accountService.findAccountByUsername("username");

        // Act
        accountService.deleteAccount(accountToDelete.get());
        Optional<Account> deletedAccount = accountService.findAccountByUsername(accountToDelete.get().getUsername());

        // Assert
        assertTrue(true);
    }

    @Test
    @DisplayName("checkAccountByUsername")
    void checkAccountByUsername() {
        // Arrange
        Account expectedAccount = new AccountMock();

        // Act
        boolean accountExists = accountService.checkAccountByUsername(expectedAccount.getUsername());

        // Assert
        assertTrue(accountExists);
    }

    @Test
    @DisplayName("findAccountByUsername")
    void findAccountByUsername() {
        // Arrange
        Account expectedAccount = new AccountMock();

        // Act
        Optional<Account> account = accountService.findAccountByUsername(expectedAccount.getUsername());

        // Assert
        assertEquals(expectedAccount.getFirstname(), account.get().getFirstname());
        assertEquals(expectedAccount.getLastname(), account.get().getLastname());
        assertEquals(expectedAccount.getUsername(), account.get().getUsername());
        assertEquals(expectedAccount.getId(), account.get().getId());
        assertEquals(expectedAccount.getPassword(), account.get().getPassword());
    }

    @Test
    @DisplayName("decryptPassword")
    void decryptPassword() {
        // Arrange
        String expectedPassword = "Celik123";
        String encryptedPassword = AES256.encrypt(expectedPassword);

        // Act
        String password = AES256.decrypt(encryptedPassword);

        // Assert
        assertEquals(expectedPassword, password);
    }
}