package com.bookso.books.service;

import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.dto.OrdersUpdateDto;

import java.util.List;

/*
* @author: santhosh kumar
* @description: Interface for Orders Service
 */
public interface IOrdersService {

    public boolean createOrder(OrderInDto ordersIn);
    public List<OrdersDto> getMyOrders(Long customerId);
    public boolean updateOrderStatus(OrdersUpdateDto ordersUpdateDto);
    public List<OrdersDto> getMyOrderByStatus(Long customerId, String status);

}
