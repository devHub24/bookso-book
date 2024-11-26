package com.bookso.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@Schema(name = "OrdersUpdateDto", description = "Schema to update Orders")
public class OrdersUpdateDto {

    @NotNull(message = "Order code can't be null")
    @Schema(description = "Code of the Order", example = "Random generated Order code")
    private Long orderCode;

    @NotNull(message = "Order status cant' be null")
    @Schema(description = "Status code of the order", example = "OS1")
    private String orderStatus;
}
