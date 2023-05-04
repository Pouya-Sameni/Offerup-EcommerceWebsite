package com.offerup.auctionservice.repos;

import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForwardAuctionDB extends MongoRepository<ForwardAuction, String> {

    ForwardAuction findByAuctionId (ObjectId id);

    List<ForwardAuction>findAllItemByItemIdAndAuctionEndedIsFalse(String id);
    List<ForwardAuction>findAllItemByItemIdAndAuctionEnded(String id, boolean auctionEnded);
    List<ForwardAuction>findAllByItemId(String id);


    List<ForwardAuction>findAllByAuctionEnded(boolean ended);

}
