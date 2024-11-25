package com.bookso.books.dto;


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
public class OrdersDto {

    private String orderCode;
    private String bookCode;
    private Long customerId;
    private double fare;
    private LocalDate orderedOn;
    private String orderStatus;


}
