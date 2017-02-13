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
public class ReminderEvent extends Event{
    private String taskName;
    private ReminderPriority priority;
    private String dueDate;
    
    public ReminderEvent(String eventId, String taskName, 
            ReminderPriority priority, String dueDate, long timestamp){
        setEventId(eventId);
        setEventType(EventType.reminderEvent);
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        setTimeStamp(timestamp);
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the priority
     */
    public ReminderPriority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(ReminderPriority priority) {
        this.priority = priority;
    }

    /**
     * @return the dueDate
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public static ReminderPriority getReminderPriority(int seed){
    	switch(seed % 3){
    		case 0: return ReminderPriority.HIGH;
    		case 1: return ReminderPriority.MEDIUM;
    		default: return ReminderPriority.LOW;
    	}
    }
}

enum ReminderPriority{
    HIGH, MEDIUM, LOW;
}