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
public class TradeAmendedEvent extends TradeEvent{
    
    private String oldValue;
    private String newValue;
    
    public TradeAmendedEvent(String eventID, String tradeIdentifier,
            String eventTime, String oldValue,
            String newValue, long timestamp) {
        setEventId(eventID);
        setEventType(EventType.tradeEvent);
        setTradeIdentifier(tradeIdentifier);
        setEventTime(eventTime);
        setTimeStamp(timestamp);
        setTradeEventType(TradeEventType.TradeAmendedEvent);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * @return the oldValue
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * @param oldValue the oldValue to set
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @return the newValue
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * @param newValue the newValue to set
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    
}
