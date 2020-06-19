package co.daniel.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"co.daniel.springboot.app.commons.models.entity"})
public class SpringbootAppProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAppProductosApplication.class, args);
    }

}
