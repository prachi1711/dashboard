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
abstract public class TradeEvent extends Event{
    private String tradeIdentifier;
    private String eventTime;
    private TradeEventType tradeEventType;

    /**
     * @return the tradeIdentifier
     */
    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    /**
     * @param tradeIdentifier the tradeIdentifier to set
     */
    public void setTradeIdentifier(String tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    /**
     * @return the eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * @return the tradeEventType
     */
    public TradeEventType getTradeEventType() {
        return tradeEventType;
    }

    /**
     * @param tradeEventType the tradeEventType to set
     */
    public void setTradeEventType(TradeEventType tradeEventType) {
        this.tradeEventType = tradeEventType;
    }
}
