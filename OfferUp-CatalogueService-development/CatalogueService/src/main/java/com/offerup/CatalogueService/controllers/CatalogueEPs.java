package com.offerup.CatalogueService.controllers;

import com.offerup.CatalogueService.dtos.Item;
import com.offerup.CatalogueService.dtos.Shipping;
import com.offerup.CatalogueService.services.CreateItemsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.offerup.CatalogueService.services.ReadItemsService;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/catalogue")
public class CatalogueEPs {


    private ReadItemsService readItemsService;
    private CreateItemsService createItemsService;

    @Autowired
    public CatalogueEPs (ReadItemsService readItemsService, CreateItemsService createItemsService){
        this.readItemsService = readItemsService;
        this.createItemsService = createItemsService;
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Item>> searchItemByName(@RequestParam(value = "name") String name) {
        if(name == null){
            return ResponseEntity.badRequest().build();
        }
        List<Item> items = readItemsService.searchItemByName(name);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search/id")
    public ResponseEntity <Item> searchItemById(@RequestParam(value = "itemId")String id){

        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        Item item = readItemsService.searchItemById(objectId);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<Item>> searchItemByDescription(@RequestParam(value = "description") String description){

    Set<Item> items = readItemsService.searchItemByDescription(description);
    if(items.isEmpty() || items == null){
        return ResponseEntity.notFound().build();
    }
        return new ResponseEntity<>(new ArrayList<>(items),HttpStatus.OK);

    }

    @GetMapping("/search/shipping")
    public ResponseEntity<List<Shipping>> searchItemByShipping(@RequestParam(value = "id") String id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);

        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
        List<Shipping> shipping = readItemsService.searchItemByShipping(objectId);
        return  ResponseEntity.ok(shipping);

    }

    @PostMapping("/create")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item items = createItemsService.addItem(item);
        return ResponseEntity.ok(items);
    }






    @GetMapping("/search/all")
    @CrossOrigin(origins = "*")
    public List<Item> testDB() {


        return readItemsService.getAllItems();
    }



}
