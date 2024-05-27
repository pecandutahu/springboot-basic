package com.seventonine.order.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seventonine.order.models.Items;
import com.seventonine.order.services.ItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/items")

@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<Items> createItem(@RequestBody Items items) {
        log.info("null", items);

        Items createdItems = itemService.saveItems(items);
        return new ResponseEntity<>(createdItems, HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public List<Items> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Items> getDetailItems(@PathVariable Integer id) {
        Optional<Items> item = itemService.getItemById(id);
        if(item.isPresent()) {
            return ResponseEntity.ok(item.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable Integer id, @RequestBody Items item) { 
        Items updatedItems = itemService.updateItems(id, item);
        return ResponseEntity.ok(updatedItems);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
