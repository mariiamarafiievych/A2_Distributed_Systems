package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL)
    private List<Item> addedItems;

    public Supplier(){

    }

    public Supplier(String firstName, String lastName, List<Item> addedItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addedItems = addedItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddedItems(List<Item> addedItems) {
        this.addedItems = addedItems;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public List<Item> getAddedItems(){
        return addedItems;
    }

    public void addItemToList(Item item){
        addedItems.add(item);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addedItems=" + addedItems +
                '}';
    }
}
