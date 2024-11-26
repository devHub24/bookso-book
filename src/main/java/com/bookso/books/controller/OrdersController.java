package com.bookso.books.controller;

import com.bookso.books.dto.*;
import com.bookso.books.service.IOrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookso.books.constatns.OrdersConstants.ORDER_CREATED_SC;
import static com.bookso.books.constatns.OrdersConstants.ORDER_UPDATE_SC;
import static com.bookso.books.constatns.StatusConstants.*;

@RestController
@RequestMapping("/bookso/v1")
@Tag(name = "Orders API", description = "APIs to deal with Orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @Operation(summary = "Create Order API", description = "API to create a new Order")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping("/orders")
    public ResponseEntity<BaseResponseDto> createOrder(@Valid @RequestBody OrderInDto orderInDto){
        ordersService.createOrder(orderInDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().code(STATUS_201).message(ORDER_CREATED_SC).build());
    }

    @Operation(summary = "Fetch Order API", description = "API to Fetch Order by Customer Id")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = OrdersDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<OrdersDto>> getMyOrders(@Valid @PathVariable("customerId") Long customerId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ordersService.getMyOrders(customerId));
    }

    @Operation(summary = "Update Order Status API", description = "API to Update the status of a Order")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PutMapping("/orders")
    public ResponseEntity<BaseResponseDto> updateOrderStatus(@Valid @RequestBody OrdersUpdateDto ordersUpdateDto){
        ordersService.updateOrderStatus(ordersUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(STATUS_200).message(ORDER_UPDATE_SC).build());
    }

    @Operation(summary = "Fetch Order by status API", description = "API to fetch a customer's orders by status")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = OrdersDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/status/{customerId}/{status}/orders")
    public ResponseEntity<List<OrdersDto>> getMyOrderByStatus(@Valid @PathVariable("customerId") Long customerId,
                                                              @Valid @PathVariable("status") String status){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ordersService.getMyOrderByStatus(customerId, status));
    }
}
