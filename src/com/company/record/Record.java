package com.company.record;

import java.util.Date;
import java.text.SimpleDateFormat;

public final class Record{
    private Date date;
    private Importance importance;
    private String source;
    private String message;

    public Record(Date date, Importance importance, String source, String message){
        this.date = date;
        this.importance = importance;
        this.source = source;
        this.message = message;
    }

    public String toString() {
        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date) + " " + String.format("%5s", importance)
                        + " " + source + " " + message;
    }

    public Date getTime(){
        return (Date)date.clone();
    }

    public Importance getImportance(){
        return importance;
    }

    public String getSource(){
        return source.trim();
    }

    public String getMessage(){ return message.replaceAll("\\-", ""); }
}
