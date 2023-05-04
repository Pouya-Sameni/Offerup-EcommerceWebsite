package com.offerup.auctionservice.repos;

import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.DirectoryIteratorException;
import java.util.List;

@Repository
public interface DutchAuctionDB extends MongoRepository<DutchAuction, String> {
    DutchAuction findByAuctionId (ObjectId id);

    DutchAuction findByItemId(String itemId);


    List<DutchAuction>findAllByItemId(String id);
    List<DutchAuction>findAllByItemIdAndAuctionEnded(String id, boolean auctionEnded);
    List<DutchAuction>findAllItemByItemIdAndAuctionEndedIsFalse(String id);


    List<DutchAuction>findAllByAuctionEnded(boolean ended);


}
