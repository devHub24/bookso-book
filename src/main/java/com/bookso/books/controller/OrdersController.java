package com.bookso.books.controller;

import com.bookso.books.dto.BaseResponseDto;
import com.bookso.books.dto.OrderInDto;
import com.bookso.books.service.IOrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bookso.books.constatns.OrdersConstants.ORDER_CREATED_SC;
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
}
