package com.bookso.books.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "books")
@Data
@ToString
public class BooksRecord {

    private String message;
    private Map<String, String> contactInfo;

}
