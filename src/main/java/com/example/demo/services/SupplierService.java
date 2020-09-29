package com.example.demo.services;

import com.example.demo.entities.Supplier;
import com.example.demo.entities.Storage;
import com.example.demo.entities.Item;
import com.example.demo.repo.SupplierRepository;
import com.example.demo.repo.StorageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, StorageRepository storageRepository){
        this.supplierRepository = supplierRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional
    public List<Supplier> getSupplier(){
        return supplierRepository.findAll();
    }

    @Transactional
    public Supplier findSupplierByName(String firstName, String lastName){
        return supplierRepository.findSupplierByName(firstName, lastName);
    }

    @Transactional
    public void addItemsToStorage(Supplier supplier, List<Item> toStorage, List<Integer> itemQuantities){
        supplierRepository.save(supplier);

        for(int i = 0; i < toStorage.size(); i++){
            Item itemTemp = toStorage.get(i);
            int itemQTemp = itemQuantities.get(i);
            Storage existsStorageItem = storageRepository.findByItem(itemTemp);

            if(existsStorageItem == null){
                Storage storageItem = new Storage();
                storageItem.setItem(itemTemp);
                storageItem.setQuantity(itemQTemp);
                storageRepository.save(storageItem);
            }else {
                int itemQuantity = existsStorageItem.getQuantity();
                int newThingQuantity = itemQuantity + itemQTemp;
                existsStorageItem.setQuantity(newThingQuantity);
                storageRepository.save(existsStorageItem);
            }

        }
    }

    @Transactional
    public void addSupplier(Supplier supplier){
        supplierRepository.save(supplier);
    }

    @Transactional
    public Supplier getSupplierById( UUID id) throws NotFoundException {
        Optional<Supplier> client = supplierRepository.findById(id);
        if (client.isPresent())
            return client.get();
        else
            throw new NotFoundException(String.format("Courier with %s was not found",id));
    }

    @Transactional
    public void deleteSupplierById(UUID id) throws NotFoundException {
        supplierRepository.delete(getSupplierById(id));
    }
}
