package at.ac.fhcampuswien.jarvis.view;
import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.security.AES256;
import at.ac.fhcampuswien.jarvis.service.AccountService;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class DeleteAccountCli {

    private final AccountService accountService;

    DeleteAccountCli(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean showCli(Account account) {
        System.out.println();
        System.out.println("DELETE ACCOUNT WAS SELECTED");
        System.out.println();

        System.out.print("Password: ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();

        if (!password.equals(AES256.decrypt(account.getPassword()))) {
            System.out.println();
            System.out.println("Password is incorrect.");
            System.out.println();

            return false;
        }

        accountService.deleteAccount(account);

        System.out.println();
        System.out.println("Account with username @" + account.getUsername() + " was deleted successfully.");
        System.out.println();

        return true;
    }
}
