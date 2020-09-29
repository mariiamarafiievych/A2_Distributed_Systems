package com.example.demo.controllers;

import com.example.demo.entities.Item;
import com.example.demo.entities.dto.ItemsDTO;
import com.example.demo.services.ItemService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> show() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws NotFoundException {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

}
