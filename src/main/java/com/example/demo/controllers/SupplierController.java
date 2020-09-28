package com.example.demo.controllers;

import com.example.demo.entities.Supplier;
import com.example.demo.entities.Item;
import com.example.demo.entities.dto.SupplyDTO;
import com.example.demo.services.SupplierService;
import com.example.demo.services.ItemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suply")
public class SupplierController {
    private final SupplierService supplierService;
    private final ItemService itemService;

    @Autowired
    public SupplierController(SupplierService supplierService, ItemService itemService) {
        this.supplierService = supplierService;
        this.itemService = itemService;
    }

    @PostMapping("suplyItems")
    public ResponseEntity<Void> getItemsFromSupplier(@RequestBody String supplyJson){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        SupplyDTO supply = gson.fromJson(supplyJson, SupplyDTO.class);
        List<Item> toStorage = supply.getItems();
        System.out.println(supply);
        List<Integer> itemQuantities = supply.getItemQuantities();
        Supplier jsonSupplier = supply.getSupplier();
        //System.out.println(seller);

        Supplier supplier = supplierService.findSupplierByName(jsonSupplier.getFirstName(), jsonSupplier.getLastName());
        System.out.println(supplier);
        //sellerService.addSeller(seller);
        for(Item t: toStorage){
            t.setAddedBy(supplier);
            System.out.println(t);
            itemService.addItem(t);
        }
        supplierService.addItemsToStorage(supplier, toStorage, itemQuantities);

        return ResponseEntity.ok().build();
    }
}
