package com.offerup.controller.Services;


import com.offerup.controller.dtos.accountsDTOs.UserDto;
import com.offerup.controller.dtos.auctionDTOs.Auction;
import com.offerup.controller.dtos.auctionDTOs.AuctionType;
import com.offerup.controller.dtos.auctionDTOs.DutchAuction;
import com.offerup.controller.dtos.auctionDTOs.ForwardAuction;
import com.offerup.controller.dtos.catalogueDTOs.Item;
import com.offerup.controller.dtos.paymentDTOs.Payment;
import com.offerup.controller.dtos.paymentDTOs.Receipt;
import com.offerup.controller.interfaces.AuctionAccessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ControllerServices {

    private CatalogueService catalogueService;
    private AuctionService auctionService;
    private  AccountsService accountsService;
    private PaymentService paymentService;


    @Autowired
    public ControllerServices (CatalogueService catalogueService, AuctionService auctionService,
                               AccountsService accountsService, PaymentService paymentService)
    {
        this.catalogueService = catalogueService;
        this.accountsService = accountsService;
        this.auctionService = auctionService;
        this.paymentService = paymentService;
    }


    public List<Auction> makeAuctionSearch (String itemDescription)
    {
        List<String> itemIds = catalogueService.getAllMatchingItemsId(itemDescription);
        if (itemIds == null || itemIds.isEmpty())
        {
            return null;
        }

        List<Auction> auctions = auctionService.getAuctionListWItemId(itemIds);

        return auctions;

    }

    public Map<String, Object> getPaymentPreview (String auctionId, String token)
    {
        try{
            Map <String, Object> paymentPreview = new HashMap<>();
            //Get User Information
            UserDto user = this.accountsService.getUserDetails(token);
            if (user == null)
            {
                return null;
            }


            Auction auction = null;
            AuctionType type = auctionService.getAuctionType(auctionId);
            AuctionAccessors auctionAccessors = AuctionAccessorFactory.createAuctionAccess(type);


            auction = auctionAccessors.auctionDetails(auctionId);




            paymentPreview.put("userInfo", user);
            paymentPreview.put("auctionInfo", auction);
            paymentPreview.put("itemInfo", catalogueService.getItemDetails(auction.getItemId()));

            return paymentPreview;

        }catch (Exception e)
        {
            return  null;
        }

    }

    public Receipt payForAuction (String auctionId, String token, Payment payment)
    {
        try{
            Map <String, Object> paymentInfo = new HashMap<>();
            //Get User Information
            UserDto user = this.accountsService.getUserDetails(token);
            if (user == null)
            {
                return null;
            }
            paymentInfo.put("userInfo", user);

            Auction auction = null;
            AuctionType type = auctionService.getAuctionType(auctionId);
            AuctionAccessors auctionAccessors = AuctionAccessorFactory.createAuctionAccess(type);

            auction = auctionAccessors.auctionDetails(auctionId);

            paymentInfo.put("auctionId", auction.getAuctionId().toString());
            paymentInfo.put("itemId", auction.getItemId().toString());



                if (!auctionAccessors.isUserWinner(auctionId,user.getUsername())){
                    return null;
                }


            paymentInfo.put ("paymentInfo", payment);

            //Make request to payment service
            Receipt receipt = paymentService.payForAuction(paymentInfo);
            return receipt;

        }catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }



    }

}
