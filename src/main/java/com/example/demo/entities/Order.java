package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> orderedItem;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(List<Item> orderedItem, Supplier supplier, Customer customer) {
        this.orderedItem = orderedItem;
        this.supplier = supplier;
        this.customer = customer;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Item> getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(List<Item> orderedItem) {
        this.orderedItem = orderedItem;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderedItem=" + orderedItem +
                ", supplier=" + supplier +
                ", customer=" + customer +
                '}';
    }
}
