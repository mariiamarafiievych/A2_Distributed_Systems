package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repo.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final StorageRepository storageRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public OrderService(ItemRepository itemRepository, OrderRepository orderRepository, CustomerRepository customerRepository, StorageRepository storageRepository, SupplierRepository supplierRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.storageRepository = storageRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void addOrder(Supplier supplier, List<Item> requiredItems, Customer customer){
        List<Item> toOrder = new ArrayList<>();
        Supplier supplierToOrder = supplierRepository.findSupplierByName(supplier.getFirstName(), supplier.getLastName());
        for(Item t: requiredItems){
            Item item = itemRepository.findItemByName(t.getName());
            Storage storageItem = storageRepository.findByItem(item);
            if(storageItem == null){
                continue;
            }
            int itemQuantity = storageItem.getQuantity();
            if(itemQuantity > 0){
                toOrder.add(item);
                int newQuantity = itemQuantity - 1;
                storageItem.setQuantity(newQuantity);
                storageRepository.save(storageItem);
            }else {
                storageRepository.delete(storageItem);
            }
        }
        if(toOrder.size() > 0){
            Order order = new Order(toOrder, supplierToOrder, customer);
            customerRepository.save(customer);
            orderRepository.save(order);
        }
    }
    @Transactional
    public Order getOrderById(UUID id) throws NotFoundException {
        Optional<Order> tempOrder = orderRepository.findById(id);
        if (tempOrder.isPresent())
            return tempOrder.get();
        else
            throw new NotFoundException(String.format("Order with id %s was not found", id));
    }
}

