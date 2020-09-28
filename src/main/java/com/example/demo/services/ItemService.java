package com.example.demo.services;

import com.example.demo.entities.Item;
import com.example.demo.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemRepository.getItemsInStock();
    }

    @Transactional
    public void addItem(Item item){
        if(itemRepository.findItemByName(item.getName()) == null){
            itemRepository.save(item);
        }
    }
}
