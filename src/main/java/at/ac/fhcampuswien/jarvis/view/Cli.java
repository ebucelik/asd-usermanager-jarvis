package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.service.AccountService;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

@Controller
public class Cli {

    private final AccountService accountService;

    public Cli(AccountService accountService) {
        this.accountService = accountService;
    }

    enum Menu {
        REGISTRATION("Register Account"),
        LOGIN("Login"),
        LOGOUT("Logout"),
        CHANGEPASSWORD("Change Password"),
        DELETEACCOUNT("Delete Account"),
        EXIT("Exit");

        private final String menu;

        Menu(String menu) {
            this.menu = menu;
        }

        public String getMenu() {
            return menu;
        }

        public static Menu getMenuBy(String chosenMenu) {
            return switch (chosenMenu) {
                case "a" -> Menu.REGISTRATION;
                case "b" -> Menu.LOGIN;
                case "c" -> Menu.LOGOUT;
                case "d" -> Menu.CHANGEPASSWORD;
                case "e" -> Menu.DELETEACCOUNT;
                default -> Menu.EXIT;
            };
        }
    }

    public void start() {
        while(true) {
            System.out.println("a) " + Menu.REGISTRATION);
            System.out.println("b) " + Menu.LOGIN);
            System.out.println("c) " + Menu.LOGOUT);
            System.out.println("d) " + Menu.CHANGEPASSWORD);
            System.out.println("e) " + Menu.DELETEACCOUNT);
            System.out.println("f) " + Menu.EXIT);

            System.out.println();

            System.out.print("Choose a menu: ");

            Scanner scanner = new Scanner(System.in);

            Menu selectedMenu = Menu.getMenuBy(scanner.nextLine());

            switch (selectedMenu) {
                case REGISTRATION:
                    showRegistrationCli();
                    break;
                case LOGIN:
                    break;
                case LOGOUT:
                    break;
                case CHANGEPASSWORD:
                    break;
                case DELETEACCOUNT:
                    break;
                case EXIT:
                    System.out.println("Exit program...");
                    return;
            }
        }
    }

    private void showRegistrationCli() {
        RegistrationCli registrationCli = new RegistrationCli(accountService);
        registrationCli.showCli();
    }
}
