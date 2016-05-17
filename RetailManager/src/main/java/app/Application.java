package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*import org.springframework.cloud.client.discovery.EnableDiscoveryClient;*/
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@RefreshScope
@EnableAutoConfiguration
@ComponentScan("app")
@SpringBootApplication 
//@EnableEurekaClient
public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(new Object[] { Application.class }, args);
  }
}
