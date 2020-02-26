package tensquareeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TensquareEurekaApplication2 {

    public static void main(String[] args) {

        SpringApplication.run(TensquareEurekaApplication2.class, args);
    }

}
