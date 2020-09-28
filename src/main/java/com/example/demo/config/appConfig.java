package com.example.demo.config;

import com.example.demo.entities.*;
import com.example.demo.repo.SupplierRepository;
import com.example.demo.repo.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class appConfig {
    @Bean
    public CommandLineRunner demo(final ItemRepository itemRepository, final SupplierRepository supplierRepository) {
        return strings -> {
            List<Item> orderedlist = new ArrayList<Item>();
            Supplier supplier = new Supplier("Sasha", "Zaykina", orderedlist);
            //Thing dress = new Thing(NameofThing.dress, 300, Size.M, ConditionOfThing.good, seller);
            //thingRepository.save(dress);
            //seller.addThingToList(dress);
            supplierRepository.save(supplier);

        };

    }
}
