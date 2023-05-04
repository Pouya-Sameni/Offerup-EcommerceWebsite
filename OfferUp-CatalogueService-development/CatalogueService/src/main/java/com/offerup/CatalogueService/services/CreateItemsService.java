package com.offerup.CatalogueService.services;

import com.offerup.CatalogueService.dtos.Item;
import com.offerup.CatalogueService.repos.CataloguesDataBase;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItemsService {


    private CataloguesDataBase cataloguesDataBase;

    @Autowired
    public CreateItemsService(CataloguesDataBase db) {
        this.cataloguesDataBase = db;

    }

    public Item addItem(Item item) {
        item.setId(null);
        ObjectId objectId = new ObjectId();
        item.setId(objectId);
        cataloguesDataBase.insert(item);
        return item;
    }


}
