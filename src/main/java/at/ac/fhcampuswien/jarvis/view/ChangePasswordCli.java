package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.security.AES256;
import at.ac.fhcampuswien.jarvis.service.AccountService;

import java.util.Scanner;

public class ChangePasswordCli {

    private final AccountService accountService;

    ChangePasswordCli(AccountService accountService) {
        this.accountService = accountService;
    }

    public void showCli(Account account) {

    while (true) {
        System.out.println();
        System.out.println("CHANGEPASSWORD WAS SELECTED");
        System.out.println();

        System.out.print("Old password: ");
        Scanner scanner = new Scanner(System.in);
        String oldPassword = scanner.nextLine();

        if (!oldPassword.equals(AES256.decrypt(account.getPassword()))) {
            System.out.println();
            System.out.println("Password is incorrect");
            System.out.println();

            break;
        }

        System.out.print("New password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Repeat password: ");
        String repeatedNewPassword = scanner.nextLine();

        if (!repeatedNewPassword.equals(newPassword)) {
            System.out.println();
            System.out.println("Passwords do not match");
            System.out.println();

            break;
        }

        account.setPassword(newPassword);
        accountService.createAccount(account);

        System.out.println();
        System.out.println("Account with username @" +
            account.getUsername() +
                " changed its password successfully.");
        System.out.println();

        break;
    }
    }
}