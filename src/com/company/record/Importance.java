package com.company.record;

public enum Importance {
    FAILURE_RECOVERY("1", "."),
    WARNING("2", "!"),
    SERIOUS_ERROR_MESSAGE("3", "!!!"),
    CRITICAL_ERROR_MESSAGE("4", "!!!!!");

    private final String textMessage;
    private final String messageSource;

    Importance(String textMessage, String messageSource){
        this.textMessage = textMessage;
        this.messageSource = messageSource;
    }

    public String getTextMessage(){
        return textMessage;
    }

    public  String getMessageSource(){
        return messageSource;
    }
}
