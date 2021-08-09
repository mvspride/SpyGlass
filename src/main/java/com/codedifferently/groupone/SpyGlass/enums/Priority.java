package com.codedifferently.groupone.SpyGlass.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


public enum Priority {
    @JsonProperty("low")
    LOW("low"),
    @JsonProperty("medium")
    MEDIUM("medium"),
    @JsonProperty("high")
    HIGH("high");

    private String priority;

    private Priority(String priority){
        this.priority = priority;
    }
}
