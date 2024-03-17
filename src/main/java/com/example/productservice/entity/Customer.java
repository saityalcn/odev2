package com.example.productservice.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Customer {
    private String name;
    private String sector;
    private List<Double> invoices;

    public Customer(String name, String sector) {
        this.name = name;
        this.sector = sector;
        this.invoices = new ArrayList<>();
    }

}
