package com.codedifferently.groupone.SpyGlass.enums;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Enumerated;


public enum Frequency {
    DAILY,
    WEEKLY,
    BI_WEEKLY,
    MONTHLY,
    BI_MONTHLY

//    private int frequencyDaysValue;
//
//    Frequency(int frequencyDaysValue){
//        this.frequencyDaysValue =frequencyDaysValue;
//    }
//    public int getFrequencyDaysValue(){
//        return frequencyDaysValue;
//    }

}
