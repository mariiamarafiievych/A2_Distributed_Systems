package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Supplier;
import com.example.demo.entities.Item;
import com.example.demo.entities.dto.CreateOrderDTO;
import com.example.demo.entities.dto.OrdersDTO;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDTO createOrder){
        Supplier supplier = createOrder.getSupplier();
        Customer customer = createOrder.getCustomer();
        List<Item> items = createOrder.getItems();
        orderService.addOrder(supplier, items, customer);
        System.out.println(supplier);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getAll")
    public @ResponseBody
    OrdersDTO getAllOrders(){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders(orderService.getAllOrders());
        return ordersDTO;
    }
}
