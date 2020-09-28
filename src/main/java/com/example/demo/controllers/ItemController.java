package com.example.demo.controllers;

import com.example.demo.entities.dto.ItemsDTO;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("getAll")
    public @ResponseBody
    ItemsDTO getAllItems(){
        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setItems(itemService.getAllItems());
        return itemsDTO;
    }
}
