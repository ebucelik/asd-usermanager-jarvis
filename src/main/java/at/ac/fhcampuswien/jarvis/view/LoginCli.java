package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.security.AES256;
import at.ac.fhcampuswien.jarvis.service.AccountService;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class LoginCli {

    public Optional<Account> account;
    private final AccountService accountService;
    private Calendar futureCalendar = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
    int attempt = 0;

    LoginCli(AccountService accountService) {
        this.accountService = accountService;
    }

    public void showCli() {
        System.out.println();

        System.out.println("LOGIN WAS SELECTED");

        System.out.println();

        if (attempt >= 4) {
            if (Calendar.getInstance().getTime().after(futureCalendar.getTime())) {
                attempt = 0;
            } else {
                System.out.println("User is locked until " + simpleDateFormat.format(futureCalendar.getTime()) + " at " + simpleTimeFormat.format(futureCalendar.getTime()));
                System.out.println();
            }
        }

        int maxAttempt = 4;
        for(; attempt < maxAttempt; attempt++) {
            System.out.print("Username: ");

            Scanner usernameScanner = new Scanner(System.in);
            String username = usernameScanner.nextLine();

            System.out.print("Password: ");

            Scanner passwordScanner = new Scanner(System.in);
            String password = passwordScanner.nextLine();

            System.out.println();

            Optional<Account> optionalAccount = accountService.findAccountByUsername(username);

            if (optionalAccount.isEmpty()) {
                System.out.println("Username or password is not correct.");
            } else if (!AES256.decrypt(optionalAccount.get().getPassword()).equals(password)){
                System.out.println("Username or password is not correct.");
            } else {
                account = optionalAccount;
                attempt = 0;

                System.out.println("Login with username @" + account.get().getUsername() + " was successful.");

                System.out.println();
                break;
            }

            System.out.println((maxAttempt - (attempt + 1)) + " attempts left.");

            System.out.println();

            if ((maxAttempt - (attempt + 1)) == 0) {
                futureCalendar = Calendar.getInstance();
                futureCalendar.add(Calendar.SECOND, 60);
            }
        }
    }
}
