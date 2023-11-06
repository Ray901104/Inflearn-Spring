package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemServiceApplication {
//public class ItemServiceApplication implements WebMvcConfigurer { // WebDataBinder 글로벌 설정방법

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	// WebDataBinder 글로벌 설정방법
//	@Override
//	public Validator getValidator() {
//		return new ItemValidator();
//	}
}
