package com.bookso.books.service;

import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;

import java.util.List;

/*
* @author: santhosh kumar
* @description: Interface for Orders Service
 */
public interface IOrdersService {

    public boolean createOrder(OrderInDto ordersIn);
    public List<OrdersDto> getMyOrders(String customerId);
    public boolean updateOrder(String orderCode, OrdersDto ordersDto);
    public boolean updateOrderStatus(String orderCode, String orderStatus);
}
