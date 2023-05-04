package com.offerup.CatalogueService.repos;

import com.offerup.CatalogueService.dtos.Item;
import com.offerup.CatalogueService.dtos.Shipping;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CataloguesDataBase extends MongoRepository<Item, String> {

    List<Item> findAllByDescriptionContainingIgnoreCase (String description);

    List<Item> findAllByItemNameContainingIgnoreCase (String name);

    Item findItemsById (ObjectId id);




}
