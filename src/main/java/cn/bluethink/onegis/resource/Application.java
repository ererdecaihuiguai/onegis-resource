package cn.bluethink.onegis.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement
@RestController
@SpringBootApplication
public class Application {
	
	@GetMapping("/")
	public String welcome(){
		return "<a href=\"swagger-ui.html\">Welcome to OneGIS REST API.</a>";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}