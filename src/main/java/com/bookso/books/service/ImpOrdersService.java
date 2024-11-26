package com.bookso.books.service;

import com.bookso.books.constatns.OrdersConstants;
import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.entity.Orders;
import com.bookso.books.enums.OrderErrors;
import com.bookso.books.exceptions.BooksoException;
import com.bookso.books.mapper.OrdersMapper;
import com.bookso.books.repo.IOrdersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


/*
* @author: santhosh kumar
* @description: Implementation class of Orders Services
 */
@Service
@Slf4j
public class ImpOrdersService implements IOrdersService{
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private IOrdersRepository ordersRepo;
    @Autowired
    private IBooksService booksService;

    private final String defaultOrderStatus = "OS1";

    /*
    * @author: santhosh kumar
    * @description: Method to create an order
    * @params: Order In dto contains: CustomerId and BookCode
    * @returns: True or False
     */
    @Override
    public boolean createOrder(OrderInDto ordersIn) {
        try{
            Orders order = ordersMapper.ordersInToOrders(ordersIn);
            order.setOrderCode(generateOrderCode());
            order.setFare(booksService.getBooksByCode(order.getBookCode()).getRate());
            order.setOrderedOn(LocalDate.now());
            order.setOrderStatus(getOrderStatus(defaultOrderStatus));
            ordersRepo.save(order);
            log.info("Order Placed");
            return true;
        }catch(DataAccessException e){
            log.error("Error while placing an order, cause:{}", e.getMessage());
            throw new BooksoException(OrderErrors.GENERIC_ERROR, "Error while placing an order");
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to fetch orders based on customer id
    * @params: customerId
    * @returns: List of orders of that customer
     */
    @Override
    @Transactional(rollbackOn = {DataAccessException.class, BooksoException.class})
    public List<OrdersDto> getMyOrders(Long customerId) {
        try(Stream<Orders> ordersStream = ordersRepo.fetchMyOrders(customerId)){
            List<OrdersDto> ordersDtoList = ordersStream.map(order -> ordersMapper.toOrdersDto(order)).toList();
            if(ordersDtoList.isEmpty()){
                log.error("No order found for the customer id:{}", customerId);
                throw new BooksoException(OrderErrors.GENERIC_ERROR, OrdersConstants.NO_ORDER_FOUND);
            }
            return ordersDtoList;
        }catch (DataAccessException e){
            log.error("Error while fetching orders cause:{}",e.getMessage());
            throw new BooksoException(OrderErrors.GENERIC_ERROR, "Error while fetching orders");
        }

    }

    @Override
    public boolean updateOrder(Long orderCode, OrdersDto ordersDto) {
        return false;
    }

    @Override
    public boolean updateOrderStatus(Long orderCode, String orderStatus) {
        return false;
    }

    /*
    * @author: santhosh kumar
    * @description: Method to generate Order Code
    * @params:
    * @returns: Randomly generated 4-digit code
     */
    private Long generateOrderCode(){
        return 10000L+(new Random().nextInt(90000));
    }

        /*
        * @author: santhosh Kumar
        * @description: Method to get the order status by order status code
        * @params: Order status Code: OS1
        * @returns: constant value of Order Status
         */
    private String getOrderStatus(String orderStatusCode){
        return OrdersConstants.orderStatusMaps.get(orderStatusCode);
    }
}
