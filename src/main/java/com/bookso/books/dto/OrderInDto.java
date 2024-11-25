package com.bookso.books.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* @author: santhosh kumar
* @description: Dto class for Order input
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInDto {

    @NotNull(message = "Customer Id can't be null")
    private Long customerId;
    @NotNull(message = "Book Code can't be null")
    @NotEmpty(message = "Book Code can't be empty")
    private String bookCode;
}
