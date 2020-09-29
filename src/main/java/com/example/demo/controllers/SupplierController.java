package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.entities.Supplier;
import com.example.demo.entities.Item;
import com.example.demo.entities.dto.SupplyDTO;
import com.example.demo.services.SupplierService;
import com.example.demo.services.ItemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
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

    @GetMapping("{id}")
    public ResponseEntity<Supplier> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> show(){
        return ResponseEntity.ok(supplierService.getSupplier());
    }
}
