package com.bookso.books.entity;

import jakarta.persistence.Column;
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
    private Long orderCode;
    private Long bookCode;
    private Long customerId;
    private double fare;
    @Column(updatable = false)
    private LocalDate  orderedOn;
    private String orderStatus;
}
