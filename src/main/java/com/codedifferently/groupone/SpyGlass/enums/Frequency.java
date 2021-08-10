package com.codedifferently.groupone.SpyGlass.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Enumerated;


public enum Frequency {
    @JsonProperty("daily")
    DAILY("daily"),
    @JsonProperty("weekly")
    WEEKLY("weekly"),
    @JsonProperty("bi-weekly")
    BI_WEEKLY("bi-weekly"),
    @JsonProperty("monthly")
    MONTHLY("monthly"),
    @JsonProperty("bi-monthly")
    BI_MONTHLY("bi-monthly");

    private String frequency;

    private Frequency(String frequency){
        this.frequency = frequency;
    }

//    private int frequencyDaysValue;
//
//    Frequency(int frequencyDaysValue){
//        this.frequencyDaysValue =frequencyDaysValue;
//    }
//    public int getFrequencyDaysValue(){
//        return frequencyDaysValue;
//    }

}
