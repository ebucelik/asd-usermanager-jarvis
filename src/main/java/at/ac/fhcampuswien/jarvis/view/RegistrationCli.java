package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.service.AccountService;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.Scanner;

@Controller
public class RegistrationCli {

    private final AccountService accountService;

    RegistrationCli(AccountService accountService) {
        this.accountService = accountService;
    }

    public void showCli() {
        String firstname = "";
        String lastname = "";
        String username = "";
        String password = "";

        System.out.println();

        System.out.println("REGISTRATION WAS SELECTED");

        System.out.println();

        while(true) {
            if (firstname.isEmpty()) {
                System.out.print("Firstname: ");

                Scanner firstnameScanner = new Scanner(System.in);
                firstname = firstnameScanner.nextLine();
            }

            if (lastname.isEmpty()) {
                System.out.print("Lastname: ");

                Scanner lastnameScanner = new Scanner(System.in);
                lastname = lastnameScanner.nextLine();
            }

            if (username.isEmpty() || accountService.checkAccountByUsername(username)) {
                username = "";

                System.out.print("Username: ");

                Scanner usernameScanner = new Scanner(System.in);
                username = usernameScanner.nextLine();
            }

            if (password.isEmpty()) {
                System.out.print("Password: ");

                Scanner passwordScanner = new Scanner(System.in);
                password = passwordScanner.nextLine();
            }

            if(firstname.isEmpty()) {
                System.out.println();
                System.out.println("Firstname is empty.");
                System.out.println();
            } else if (lastname.isEmpty()) {
                System.out.println();
                System.out.println("Lastname is empty.");
                System.out.println();
            } else if (username.isEmpty()) {
                System.out.println();
                System.out.println("Username is empty.");
                System.out.println();
            } else if (password.isEmpty()) {
                System.out.println();
                System.out.println("Password is empty.");
                System.out.println();
            } else if (accountService.checkAccountByUsername(username)) {
                System.out.println();
                System.out.println("This username is already used.");
                System.out.println();
            } else {
                break;
            }
        }

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
