package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier addedBy;

    public Item() {

    }


    public Item(String name, double price, Supplier addedBy) {
        this.name = name;
        this.price = price;
        this.addedBy = addedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Supplier addedBy){
        this.addedBy = addedBy;
    }
    public double getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", addedBy=" + addedBy +
                '}';
    }
}
