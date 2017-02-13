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
public class ExceptionEvent extends Event{
    
    private String code;
    private ExceptionSeverity severity;
    private String description;
    private ExceptionStatus status;
    
    public ExceptionEvent(String eventID, String code, 
            ExceptionSeverity severity, String description, long timeStamp){
        setEventId(eventID);
        setEventType(EventType.exceptionEvent);
        setTimeStamp(timeStamp);
        this.code = code;
        this.severity = severity;
        this.description = description;
        this.status = ExceptionStatus.OPEN;
        
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the severity
     */
    public ExceptionSeverity getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(ExceptionSeverity severity) {
        this.severity = severity;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

	public ExceptionStatus getStatus() {
		return status;
	}

	public void setStatus(ExceptionStatus status) {
		this.status = status;
	}
    
    
}

enum ExceptionSeverity{
    CRITICAL, HIGH, MEDIUM, LOW
}

enum ExceptionStatus{
    OPEN, CLOSED	
}

