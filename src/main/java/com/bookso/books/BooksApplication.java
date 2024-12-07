package com.bookso.books;

import com.bookso.books.dto.BooksRecord;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "booksAudit")
@OpenAPIDefinition(info = @Info(title = "Books MS", description = "Books Service",
		license = @License(name = "Apache 2.1"),
		contact = @Contact(name = "santhosh Kumar" , email = "santhoshsk25420@gmail.com"),
		version = "V1.1",
		summary = "Books Ms that has both Books and Orders APIS"))
@EnableConfigurationProperties(BooksRecord.class)
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}

}
