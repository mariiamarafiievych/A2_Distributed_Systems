package com.example.demo.services;

import com.example.demo.entities.Item;
import com.example.demo.repo.ItemRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Transactional
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item getItemById(UUID id) throws NotFoundException {
        Optional<Item> tempItem = itemRepository.findById(id);
        if (tempItem.isPresent())
            return tempItem.get();
        else
            throw new NotFoundException(String.format("Item with id %s was not found", id));
    }

    @Transactional
    public void deleteItemById(UUID id) throws NotFoundException {
        itemRepository.delete(getItemById(id));
    }

}
