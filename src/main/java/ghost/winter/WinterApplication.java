package ghost.winter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class WinterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinterApplication.class, args);
    }

}
