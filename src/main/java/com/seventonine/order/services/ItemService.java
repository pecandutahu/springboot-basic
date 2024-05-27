package com.seventonine.order.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventonine.order.models.Items;
import com.seventonine.order.repositories.ItemsRepository;

@Service
public class ItemService {
    @Autowired
    
    private ItemsRepository itemsRepository;

    public Items saveItems(Items items) {
        return itemsRepository.save(items);
    }

    public List<Items> getAllItems(){
        return itemsRepository.findAll();
    }

    public Optional<Items> getItemById(Integer Id) {
        return itemsRepository.findById(Id);
    }

    public Items updateItems(Integer id, Items itemDetails) {
        Items items = itemsRepository.findById(id).orElseThrow();
        items.setItemsName(itemDetails.getItemsName());
        items.setItemsCode(itemDetails.getItemsCode());
        items.setStock(itemDetails.getStock());
        items.setPrice(itemDetails.getPrice());
        items.setIsAvailable(itemDetails.getIsAvailable());
        items.setLastReStock(itemDetails.getLastReStock());
        return itemsRepository.save(items);
    }

    public void deleteItem(Integer id) {
        itemsRepository.deleteById(id);
    }
}
