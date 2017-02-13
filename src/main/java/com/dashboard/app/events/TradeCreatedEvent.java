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
public class TradeCreatedEvent extends TradeEvent {

    private String securityIdentifier;
    private int quantity;
    private float price;
    private String trader;
    
    public TradeCreatedEvent(String eventID, String tradeIdentifier,
            String eventTime, String securityIdentifier,
            int quantity, float price, String trader, long timestamp) {
        setEventId(eventID);
        setEventType(EventType.tradeEvent);
        setTradeIdentifier(tradeIdentifier);
        setEventTime(eventTime);
        setTradeEventType(TradeEventType.TradeCreatedEvent);
        setTimeStamp(timestamp);
        this.securityIdentifier = securityIdentifier;
        this.quantity = quantity;
        this.price = price;
        this.trader = trader;
    }

    /**
     * @return the securityIdentifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @param securityIdentifier the securityIdentifier to set
     */
    public void setSecurityIdentifier(String securityIdentifier) {
        this.securityIdentifier = securityIdentifier;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the trader
     */
    public String getTrader() {
        return trader;
    }

    /**
     * @param trader the trader to set
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }
}
