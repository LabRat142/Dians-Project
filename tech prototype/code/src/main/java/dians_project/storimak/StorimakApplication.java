package dians_project.storimak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StorimakApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorimakApplication.class, args);
    }

}
