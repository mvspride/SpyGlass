package com.codedifferently.groupone.SpyGlass.entities;

import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name="goal")
public class Goal {

    @Id
    @GeneratedValue
    private Long id;

    private String description = "";
    //YO Lucas, Instant is for computers; Date is for humans
    private Date deadLine;
    @CreationTimestamp
    private Timestamp timeStamp;

    @Autowired
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Autowired
    private Priority priority;

    private double contributionAmount;
    private double goalAmount;
    private double currentlySaved;
    private String pictureURL;
    @JsonIgnore
    @ManyToOne
    private User user;

    public Goal() {

    }

    /**
     * calculates the rate at which the user will be paying based on their chosen frequency and the goalAmount
     * @param frequency
     * @param goalAmount
     * @return rate
     */
   public Double calculateContributionAmount(Frequency frequency,double goalAmount){
       byte daysPerFrequency =0;
       switch (frequency){
           case DAILY:
               daysPerFrequency = 1;
               break;

           case WEEKLY:
               daysPerFrequency = 7;
               break;

           case BI_WEEKLY:
               daysPerFrequency = 14;
                break;

           case MONTHLY:
               daysPerFrequency = 30;
                break;

           case BI_MONTHLY:
               daysPerFrequency = 60;
               break;
       }
       return (goalAmount-currentlySaved)/daysPerFrequency;
   }

    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        Timestamp oldfashionedTimestamp = this.timeStamp;

        ZonedDateTime dateTime = oldfashionedTimestamp.toInstant()
                .atZone(ZoneId.systemDefault());
        return dateTime.format(formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }
    public double getContributionAmount() {
        return contributionAmount;
    }
    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public double getCurrentlySaved() {
        return currentlySaved;
    }

    public void setCurrentlySaved(double currentlySaved) {
        this.currentlySaved = currentlySaved;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setContributionAmount(Double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public void addContributionAmount(Double contributionAmount){
        this.contributionAmount += contributionAmount;
    }

    public void reduceContributionAmount(Double contributionAmount){
        this.contributionAmount -= contributionAmount;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
