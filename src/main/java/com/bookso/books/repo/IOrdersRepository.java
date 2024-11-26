package com.bookso.books.repo;

import com.bookso.books.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT order FROM Orders order WHERE order.customerId= :customerId ")
    public Stream<Orders> fetchMyOrders(@Param("customerId")Long customerId);

    @Query("SELECT order FROM Orders order WHERE order.customerId= :customerId AND order.orderStatus LIKE %:status%")
    public Stream<Orders> fetchMyOrdersByStatus(@Param("customerId") Long customerId, @Param("status")String status);
}
