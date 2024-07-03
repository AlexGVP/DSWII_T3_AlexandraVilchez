package cibertec.edu.pe.DSWII_T3_AlexandraVilchez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DswiiT3AlexandraVilchezApplication {

	public static void main(String[] args) {
		SpringApplication.run(DswiiT3AlexandraVilchezApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configGlobalCors(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("api/**")
						.allowedMethods("GET","POST","PUT")
						.allowedOrigins("https://www.cibertec.edu.pe");
			}
		};
	}
}