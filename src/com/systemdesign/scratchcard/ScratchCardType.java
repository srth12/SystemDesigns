package com.systemdesign.scratchcard;

public enum  ScratchCardType implements ScratchCard {

    CASH_REWARD{
        @Override
        public ScratchCard getReward() {
            if (scratchCard == null){
                scratchCard = new ScratchCardPriceService();
            }
            return scratchCard;
        }
    };

    ScratchCard scratchCard = null;
}
