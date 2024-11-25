package com.bookso.books.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/*
* @author: santhosh kumar
* @descriptionL Orders Entity class
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Orders extends BaseEntity {

    @Id
    private String orderCode;
    private String bookCode;
    private Long customerId;
    private double fare;
    private LocalDate  orderedOn;
    private String orderStatus;
}
