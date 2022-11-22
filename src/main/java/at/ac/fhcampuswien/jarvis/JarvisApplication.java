package at.ac.fhcampuswien.jarvis;

import at.ac.fhcampuswien.jarvis.repository.AccountRepository;
import at.ac.fhcampuswien.jarvis.service.AccountService;
import at.ac.fhcampuswien.jarvis.service.AccountServiceImpl;
import at.ac.fhcampuswien.jarvis.view.Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JarvisApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(JarvisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AccountRepository accountRepository = applicationContext.getBean(AccountRepository.class);

		AccountService accountService = new AccountServiceImpl(accountRepository);

		Cli cli = new Cli(accountService);
		cli.start();
	}
}
