package com.bookso.books.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

/*
* @author: santhosh kumar
* @description: Dto class of Orders entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Schema(name = "OrdersDto", description = "Schema for Orders")
public class OrdersDto {

    @Schema(description = "Order code", example="Random generated number")
    private Long orderCode;

    @Schema(description = "Book code", example="Random generated number")
    private Long bookCode;

    @Schema(description = "Customer Id", example="Random generated number")
    private Long customerId;

    @Schema(description = "Book fare", example="800r")
    private double fare;

    @Schema(description = "Ordered date", example="12/09/2024")
    private LocalDate orderedOn;

    @Schema(description = "Order status", example="DELIVERED")
    private String orderStatus;


}
