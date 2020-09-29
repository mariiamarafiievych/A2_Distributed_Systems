package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.Supplier;
import com.example.demo.entities.Item;
import com.example.demo.entities.dto.CreateOrderDTO;
import com.example.demo.entities.dto.OrdersDTO;
import com.example.demo.services.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderDTO createOrder){
        Supplier supplier = createOrder.getSupplier();
        Customer customer = createOrder.getCustomer();
        List<Item> things = createOrder.getItems();
        orderService.addOrder(supplier, things, customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> show(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

}
