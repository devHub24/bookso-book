package com.bookso.books.controller;

import com.bookso.books.dto.BaseResponseDto;
import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.dto.OrdersUpdateDto;
import com.bookso.books.service.IOrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookso.books.constatns.OrdersConstants.ORDER_CREATED_SC;
import static com.bookso.books.constatns.OrdersConstants.ORDER_UPDATE_SC;
import static com.bookso.books.constatns.StatusConstants.STATUS_200;
import static com.bookso.books.constatns.StatusConstants.STATUS_201;

@RestController
@RequestMapping("/bookso/v1")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping("/orders")
    public ResponseEntity<BaseResponseDto> createOrder(@Valid @RequestBody OrderInDto orderInDto){
        ordersService.createOrder(orderInDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().code(STATUS_201).message(ORDER_CREATED_SC).build());
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<OrdersDto>> getMyOrders(@Valid @PathVariable("customerId") Long customerId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ordersService.getMyOrders(customerId));
    }

    @PutMapping("/orders")
    public ResponseEntity<BaseResponseDto> updateOrderStatus(@Valid @RequestBody OrdersUpdateDto ordersUpdateDto){
        ordersService.updateOrderStatus(ordersUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(STATUS_200).message(ORDER_UPDATE_SC).build());
    }

    @GetMapping("/status/{customerId}/{status}/orders")
    public ResponseEntity<List<OrdersDto>> getMyOrderByStatus(@Valid @PathVariable("customerId") Long customerId,
                                                              @Valid @PathVariable("status") String status){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ordersService.getMyOrderByStatus(customerId, status));
    }
}
