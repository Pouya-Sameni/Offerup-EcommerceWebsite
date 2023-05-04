package com.offerup.auctionservice.forwardAuctionServices;

import com.offerup.auctionservice.dtos.ForwardAuction;
import com.offerup.auctionservice.repos.ForwardAuctionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Component
public class ForwardMonitor {

    private ForwardAuctionDB forwardAuctionDB;
    private QueryForwardService queryForwardService;
    private UpdateForwardService updateForwardService;


    @Autowired
    public ForwardMonitor (ForwardAuctionDB forwardAuctionDB, QueryForwardService queryForwardService,
                           UpdateForwardService updateForwardService){
        this.forwardAuctionDB = forwardAuctionDB;
        this. queryForwardService = queryForwardService;
        this. updateForwardService = updateForwardService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void monitorAuctions() {
        try{
            List<ForwardAuction> allAuctions = queryForwardService.getAllOpen();
            Date endDate;
            Date timeNow;
            for (ForwardAuction auction: allAuctions)
            {
                timeNow = new Date();

                // Create a TimeZone object for ETD timezone
                //TimeZone etdTimeZone = TimeZone.getTimeZone("America/Toronto");

                // Set the timezone of the Date object
                //timeNow.setTime(timeNow.getTime() + etdTimeZone.getRawOffset());

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String formattedDate = formatter.format(timeNow);

                timeNow = formatter.parse(formattedDate);
                endDate = auction.DateEndTime();

                if (timeNow.getTime() >= endDate.getTime()){
                    System.out.println(timeNow);
                    System.out.println(endDate);
                    updateForwardService.closeAuction(auction.getAuctionId().toString());
                }
            }
        }catch (Exception e)
        {
            System.out.println("Unable to end auction");
        }
    }



}
