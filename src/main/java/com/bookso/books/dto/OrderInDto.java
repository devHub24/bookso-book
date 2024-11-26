package com.bookso.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "OrderInDto", description = "Schema to get the Orders inuput details")
public class OrderInDto {

    @Schema(description = "Customer Id", example="Random generated number")
    @NotNull(message = "Customer Id can't be null")
    private Long customerId;

    @Schema(description = "Book code", example="Random generated number")
    @NotNull(message = "Book Code can't be null")
    private Long bookCode;
}
