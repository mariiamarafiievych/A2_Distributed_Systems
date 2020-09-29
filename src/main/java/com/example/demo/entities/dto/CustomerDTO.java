package com.example.demo.entities.dto;

import com.example.demo.entities.Customer;
import com.sun.istack.NotNull;

import java.util.List;

public class CustomerDTO {
    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }


}
