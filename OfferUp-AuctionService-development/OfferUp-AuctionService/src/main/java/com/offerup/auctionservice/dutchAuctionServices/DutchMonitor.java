package com.offerup.auctionservice.dutchAuctionServices;


import com.offerup.auctionservice.dtos.DutchAuction;
import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.repos.DutchAuctionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Component
public class DutchMonitor {

    private DutchAuctionDB dutchAuctionDB;
    private QueryDutchService queryDutchService;
    private UpdateDutchService updateDutchService;


    @Autowired
    public DutchMonitor(DutchAuctionDB dutchAuctionDB, QueryDutchService queryDutchService,
                        UpdateDutchService updateDutchService){
        this.dutchAuctionDB = dutchAuctionDB;
        this.queryDutchService = queryDutchService;
        this.updateDutchService = updateDutchService;
    }

    @Scheduled(fixedRate = 10000)
    public void monitorDecrement() {
        try{
            List<DutchAuction> allAuctions = queryDutchService.getAllOpen();
            for (DutchAuction auction: allAuctions)
            {
                updateDutchService.decrement(auction.getAuctionId().toString());
            }
        }catch (Exception e)
        {
            System.out.println("Unable to decrement auction");
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void monitorClosure() {
        try{
            List<DutchAuction> allAuctions = queryDutchService.getAllOpen();
            Date endDate;
            Date timeNow;
            for (DutchAuction auction: allAuctions)
            {
                timeNow = new Date();

//                // Create a TimeZone object for ETD timezone
//                TimeZone etdTimeZone = TimeZone.getTimeZone("America/New_York");
//
//                // Set the timezone of the Date object
//                timeNow.setTime(timeNow.getTime() + etdTimeZone.getRawOffset());

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String formattedDate = formatter.format(timeNow);

                timeNow = formatter.parse(formattedDate);
                endDate = auction.DateEndTime();
                if (timeNow.getTime() >= endDate.getTime()){
                    System.out.println(timeNow);
                    System.out.println(endDate);
                    updateDutchService.closeAuction(auction.getAuctionId().toString());
                }
            }
        }catch (Exception e)
        {
            System.out.println("Unable to end auction");
        }
    }

}
