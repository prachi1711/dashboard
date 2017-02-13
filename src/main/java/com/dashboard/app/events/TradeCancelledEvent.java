/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dashboard.app.events;

/**
 *
 * @author i843179
 */
public class TradeCancelledEvent extends TradeEvent{
    
    private String cancellationComments;
    
    public TradeCancelledEvent(String eventID, String tradeIdentifier,
            String eventTime, String cancellationComments, long timestamp) {
        setEventId(eventID);
        setEventType(EventType.tradeEvent);
        setTradeIdentifier(tradeIdentifier);
        setEventTime(eventTime);
        setTradeEventType(TradeEventType.TradeCancelledEvent);
        setTimeStamp(timestamp);
        this.cancellationComments = cancellationComments;
    }

    /**
     * @return the cancellationComments
     */
    public String getCancellationComments() {
        return cancellationComments;
    }

    /**
     * @param cancellationComments the cancellationComments to set
     */
    public void setCancellationComments(String cancellationComments) {
        this.cancellationComments = cancellationComments;
    }
    
}
