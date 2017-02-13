/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dashboard.app.events;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author i843179
 */
public class JsonSerializer {

	static List<List<Event>> eventMetaList = new ArrayList<List<Event>>();
	static int counter = 0;
	static long timeStamp = System.currentTimeMillis();
	static int tradeEventCounter = 1;
	static int exceptionCodeCounter = 1;
	static int traderCounter = 1;
	static int taskCounter = 1;
	static String[] exceptionMessages = {"Server timed out.", 
										"Invalid request received",
										"Message format is invalid"};
	
	static{
		
		//3 trade, 1 exception
		List<Event> eventList1 = new ArrayList<Event>();
		eventList1.add(new ExceptionEvent("E001", 
	            "EX00" + exceptionCodeCounter++, ExceptionSeverity.CRITICAL,
	            exceptionMessages[0], timeStamp+=100000));
	    eventList1.add(new TradeAmendedEvent("E002", 
	            "T00" + tradeEventCounter++, "2015-04-17", "oldValue", "newValue", timeStamp+=100000));
	    eventList1.add(new TradeCancelledEvent("E003", 
	            "T00" + tradeEventCounter++, "2015-04-17", "Trade has been cancelled", timeStamp+=100000));
	    eventList1.add(new TradeCreatedEvent("E004",
	            "T00" + tradeEventCounter++, "2015-04-17", "SC00" + traderCounter, 40000, 1050, "Trader " + traderCounter++, timeStamp+=100000));
	    	    eventMetaList.add(eventList1);
	    eventList1.add(new ReminderEvent("E005", 
	    	            "Task" + taskCounter++, ReminderPriority.MEDIUM,"2015-04-17", timeStamp+=100000));
	    
	    //2 reminder events
	    
	    List<Event> eventList2 = new ArrayList<Event>();
	    eventList2.add(new ReminderEvent("E006", 
	            "Task" + taskCounter++, ReminderPriority.HIGH,"2015-04-17", timeStamp+=100000));
	    eventList2.add(new ReminderEvent("E007", 
	            "Task" + taskCounter++, ReminderPriority.LOW,"2015-04-17", timeStamp+=100000));
	    eventMetaList.add(eventList2);
	    
	    // 1 trade, 1 exception, 1 reminder
	    
	    List<Event> eventList3 = new ArrayList<Event>();
	    eventList3.add(new ReminderEvent("E008", 
	            "Task" + taskCounter++, ReminderPriority.MEDIUM,"2015-04-17", timeStamp+=100000));
	    eventList3.add(new ExceptionEvent("E009", 
	            "EX00" + exceptionCodeCounter++, ExceptionSeverity.HIGH,
	            "Server timed out", timeStamp+=100000));
	    eventList3.add(new TradeCreatedEvent("E010",
	            "T00" + tradeEventCounter, "2015-04-17", "SC00" + traderCounter, 40000, 1050, "Trader " + traderCounter++, timeStamp+=100000));
	    eventMetaList.add(eventList3);
	

		for(int i=11, j=0; j<=1000; i+=5, j++){
			List<Event> eventList = new ArrayList<Event>();
		    eventList.add(new ReminderEvent("E00" + i, 
		            "Task" + taskCounter++, ReminderEvent.getReminderPriority(j),"2015-04-17", timeStamp+=100000));
		    eventList.add(new ExceptionEvent("E00" + (i+1), 
		            "EX00" + exceptionCodeCounter++, 
		            ExceptionSeverity.MEDIUM,exceptionMessages[j%3], timeStamp+=100000));
		    eventList.add(new TradeAmendedEvent("E00" + (i+2), 
		            "T00" + tradeEventCounter++, "2015-04-17", "oldValue", "newValue", timeStamp+=100000));
		    eventList.add(new TradeCancelledEvent("E00" + (i+3), 
		            "T00" + tradeEventCounter++, "2015-04-17", "Trade has been cancelled", timeStamp+=100000));
		    eventList.add(new TradeCreatedEvent("E00" + (i+4),
		            "T00" + tradeEventCounter++, "2015-04-17", "SC00" + traderCounter, 40000, 1050, "Trader " + traderCounter++, timeStamp+=100000));
		    eventMetaList.add(eventList);
		}
		
        
	}
	 
    public static String fetchEvents() throws IOException{
    	
    	ObjectMapper objectMapper = new ObjectMapper();
        
        try {        	
            return objectMapper.writeValueAsString(eventMetaList.get(counter++));
        	        	
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
