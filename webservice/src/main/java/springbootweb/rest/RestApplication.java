package springbootweb.rest;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import services.ClientService;
import services.CurrencyService;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ContextConfiguration(classes = JpaConfiguration.class)
@ComponentScan(basePackages = { "controller", "services", "repository", "repository.entities", "springbootweb.rest" })
public class RestApplication {
    private static final Logger LOGGER = Logger.getLogger(RestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Backend application");
       ConfigurableApplicationContext context = SpringApplication.run(RestApplication.class, args);
       CurrencyService currencyService = context.getBean(CurrencyService.class);
        ClientService clientService = context.getBean(ClientService.class);
       currencyService.performCleanUp();
       clientService.performCleanUp();
       currencyService.getDataFromWikiPage();

    }
}
