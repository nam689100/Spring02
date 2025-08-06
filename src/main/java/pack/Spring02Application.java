package pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Spring02Application {
	@GetMapping("/")
	public String tiger() {
		return "Spring02:0911";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Spring02Application.class, args);
	}

}
