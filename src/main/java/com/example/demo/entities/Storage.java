package com.example.demo.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private Item item;
    private int quantity;

    public Storage() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
