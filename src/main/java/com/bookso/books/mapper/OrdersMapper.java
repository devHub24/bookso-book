package com.bookso.books.mapper;

import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.entity.Orders;
import org.springframework.stereotype.Service;

/*
* @author: santhosh kumar
* @description: Mapper class for Orders and its' DTOs
 */
@Service
public class OrdersMapper {

    /*
     * @author: santhosh kumar
     * @description: Method to convert Orders to OrdersDto
     * @params: Orders Entity class
     * @returns: Converted OrdersDto class
     */
    public OrdersDto toOrdersDto(Orders orders){
        return OrdersDto.builder()
                .bookCode(orders.getBookCode())
                .customerId(orders.getCustomerId())
                .orderCode(orders.getOrderCode())
                .fare(orders.getFare())
                .orderStatus(orders.getOrderStatus())
                .orderedOn(orders.getOrderedOn())
                .build();
    }

    /*
     * @author: santhosh kumar
     * @description: Method to convert OrdersDto to Orders
     * @params: OrdersDto class to be converted
     * @returns: Converted Orders class
     */
    public Orders toOrders(OrdersDto ordersDto){
        return Orders.builder()
                .bookCode(ordersDto.getBookCode())
                .customerId(ordersDto.getCustomerId())
                .orderCode(ordersDto.getOrderCode())
                .fare(ordersDto.getFare())
                .orderStatus(ordersDto.getOrderStatus())
                .orderedOn(ordersDto.getOrderedOn())
                .build();
    }

    /*
     * @author: santhosh kumar
     * @description: Converting OrdersInDto to Orders Class
     * @params: OrdersInDto class
     * @returns: Converted OrdersInDto to Orders class
     */
    public Orders ordersInToOrders(OrderInDto ordersIn){
        return Orders.builder()
                .bookCode(ordersIn.getBookCode())
                .customerId(ordersIn.getCustomerId())
                .build();
    }

}
