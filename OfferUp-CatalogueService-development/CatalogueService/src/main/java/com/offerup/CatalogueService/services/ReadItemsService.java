package com.offerup.CatalogueService.services;

import com.offerup.CatalogueService.dtos.Item;
import com.offerup.CatalogueService.dtos.Shipping;
import com.offerup.CatalogueService.repos.CataloguesDataBase;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReadItemsService {

    private CataloguesDataBase cataloguesDataBase;

    @Autowired
    public ReadItemsService(CataloguesDataBase db){
        this.cataloguesDataBase = db;
    }

    public List<Item> searchItemByName (String itemName) {
        List<Item> item = cataloguesDataBase.findAllByItemNameContainingIgnoreCase(itemName);
        return item;

    }

    public Item searchItemById (ObjectId id) {
        Item item = cataloguesDataBase.findItemsById(id);
        return item;

    }

    public Set<Item> searchItemByDescription (String description) {
        Set<Item> item = new HashSet<>();
        item.addAll(cataloguesDataBase.findAllByDescriptionContainingIgnoreCase(description));
        item.addAll(cataloguesDataBase.findAllByItemNameContainingIgnoreCase(description));
        return item;

    }

    public List<Shipping> searchItemByShipping (ObjectId id){
        Item item = searchItemById(id);
        List<Shipping> shipping = item.getShippingOptions();
        return shipping;


    }

    public List<Item> getAllItems () {
        return cataloguesDataBase.findAll();


    }


}
