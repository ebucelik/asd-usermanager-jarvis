package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.service.AccountService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class RegistrationCli {

    private final AccountService accountService;

    RegistrationCli(AccountService accountService) {
        this.accountService = accountService;
    }

    public void showCli() {
        System.out.println();

        System.out.println("REGISTRATION WAS SELECTED");

        System.out.println();

        System.out.print("Firstname: ");

        Scanner firstnameScanner = new Scanner(System.in);
        String firstname = firstnameScanner.nextLine();

        System.out.print("Lastname: ");

        Scanner lastnameScanner = new Scanner(System.in);
        String lastname = lastnameScanner.nextLine();

        System.out.print("Username: ");

        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();

        System.out.print("Password: ");

        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();

        Account account = accountService.createAccount(
                new Account(
                        firstname,
                        lastname,
                        username,
                        password
                )
        );

        System.out.println();

        System.out.println("Account with username @" + account.getUsername() + " was created successfully.");

        System.out.println();
    }
}
