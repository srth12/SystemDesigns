package com.systemdesign.scratchcard;

public class DashboardService {

    public ScratchCard scratchACard(User user, ScratchCardType scratchCardType){

        return scratchCardType.getReward();
    }
}
