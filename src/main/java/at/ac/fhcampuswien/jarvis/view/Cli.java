package at.ac.fhcampuswien.jarvis.view;

import at.ac.fhcampuswien.jarvis.models.Account;
import at.ac.fhcampuswien.jarvis.service.AccountService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class Cli {

    private final AccountService accountService;

    private final RegistrationCli registrationCli;
    private final LoginCli loginCli;
    private final ChangePasswordCli changePasswordCli;
    private final DeleteAccountCli deleteAccountCli;

    public Cli(AccountService accountService) {
        this.accountService = accountService;
        this.loginCli = new LoginCli(accountService);
        this.registrationCli = new RegistrationCli(accountService);
        this.changePasswordCli = new ChangePasswordCli(accountService);
        this.deleteAccountCli = new DeleteAccountCli(accountService);
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

            // .isPresent() does not work somehow
            if (loginCli.account != null) {
                System.out.println("c) " + Menu.LOGOUT);
                System.out.println("d) " + Menu.CHANGEPASSWORD);
                System.out.println("e) " + Menu.DELETEACCOUNT);
                System.out.println("f) " + Menu.EXIT);
            }

            System.out.println();

            System.out.print("Choose a menu: ");

            Scanner scanner = new Scanner(System.in);

            Menu selectedMenu = Menu.getMenuBy(scanner.nextLine());

            switch (selectedMenu) {
                case REGISTRATION -> showRegistrationCli();
                case LOGIN -> showLoginCli();
                case EXIT -> {
                    System.out.println("Exit program...");
                    return;
                }
            }

            if (loginCli.account != null) {
                switch (selectedMenu) {
                    case LOGOUT:
                        loginCli.account = null;
                        break;
                    case CHANGEPASSWORD:
                        showChangePasswordCli(loginCli.account.get());
                        break;
                    case DELETEACCOUNT:
                        Boolean deletedAccount = showDeleteAccountCli(loginCli.account.get());
                        if (deletedAccount) {
                            loginCli.account = null;
                        }
                        break;
                    case EXIT:
                        System.out.println("Exit program...");
                        return;
                }
            }
        }
    }

    private void showRegistrationCli() {
        registrationCli.showCli();
    }

    private void showLoginCli() {
        loginCli.showCli();
    }
    
    private void showChangePasswordCli(Account account) {
        changePasswordCli.showCli(account);
    }
    
    private Boolean showDeleteAccountCli(Account account) {
        return deleteAccountCli.showCli(account);
    }
}
