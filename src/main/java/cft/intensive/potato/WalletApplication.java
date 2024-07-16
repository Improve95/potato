package cft.intensive.potato;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class WalletApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WalletApplication.class, args);

		/*PaymentService paymentService = (PaymentService) context.getBean("paymentServiceImp");
		PaymentPostRequest paymentPostRequest = PaymentPostRequest.builder()
				.creatorId(26)
				.payerId(27)
				.amount(50)
				.comment("haha")
				.build();
		paymentService.createNewPayment(paymentPostRequest);*/
	}
}
