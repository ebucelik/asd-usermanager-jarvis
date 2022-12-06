package at.ac.fhcampuswien.jarvis;

import at.ac.fhcampuswien.jarvis.service.AccountService;
import at.ac.fhcampuswien.jarvis.view.Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        Cli cli = new Cli(accountService);
        cli.start();
    }
}
