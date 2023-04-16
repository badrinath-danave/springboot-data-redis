package springbootdataredis.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootDataRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDataRedisApplication.class, args);
	}

}
