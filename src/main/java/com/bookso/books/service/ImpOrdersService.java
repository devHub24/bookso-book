package com.bookso.books.service;

import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.mapper.BooksMapper;
import com.bookso.books.mapper.OrdersMapper;
import com.bookso.books.repo.IBooksRepository;
import com.bookso.books.repo.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
* @author: santhosh kumar
* @description: Implementation class of Orders Services
 */
@Service
public class ImpOrdersService implements IOrdersService{
    @Autowired
    private OrdersMapper booksMapper;
    @Autowired
    private IOrdersRepository ordersRepo;
    @Autowired
    private IBooksRepository booksRepo;

    /*
    * @author: santhosh kumar
    * @description: Method to create an order
    * @params: Order In dto contains: CustomerId and BookCode
    * @returns: True or False
     */
    @Override
    public boolean createOrder(OrderInDto ordersIn) {
        return false;
    }

    @Override
    public List<OrdersDto> getMyOrders(String customerId) {
        return List.of();
    }

    @Override
    public boolean updateOrder(String orderCode, OrdersDto ordersDto) {
        return false;
    }

    @Override
    public boolean updateOrderStatus(String orderCode, String orderStatus) {
        return false;
    }
}
