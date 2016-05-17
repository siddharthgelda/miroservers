package demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@EnableAutoConfiguration
@ComponentScan
@RestController
public class ClientApp {
	// @Value("${foo}")
    //String bar;
	 
	 @Value ("${message}")
	String message;
	
	 @Value ("${apikey}")
		String apikey;
		
    @RequestMapping("/message")
    String hello() {
        return "Hello " + message + apikey+"!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}