package com.example.demo.repo;

import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("SELECT it FROM Item it WHERE it.name = :name")
    Item findItemByName(@Param("name") String name);

    @Query("SELECT it FROM Item it INNER JOIN Storage s ON it.id = s.item WHERE s.quantity > 0")
    List<Item> getItemsInStock();
}
