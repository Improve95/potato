package ru.cft.template;

import ru.improve.potato.WalletApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest
class WalletApplicationTests {

	@Test
	void UserExistCheck(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WalletApplication.class, args);


	}

}
