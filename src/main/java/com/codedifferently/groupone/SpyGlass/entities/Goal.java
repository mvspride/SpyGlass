package com.codedifferently.groupone.SpyGlass.entities;

import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany
    private List<Contribution> contributions;


    /**
     * calculates the rate at which the user will be paying based on their chosen frequency and the goalAmount
     *
     * @param frequency
     * @param goalAmount
     * @return rate
     */
    public Double calculateContributionAmount(Frequency frequency, double goalAmount) {
        byte daysPerFrequency = 0;
        switch (frequency) {
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
        return (goalAmount - currentlySaved) / daysPerFrequency;
    }

    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        Timestamp oldfashionedTimestamp = this.timeStamp;

        ZonedDateTime dateTime = oldfashionedTimestamp.toInstant()
                .atZone(ZoneId.systemDefault());
        return dateTime.format(formatter);
    }

    public void addContribution(Contribution contribution) {
        contributions.add(contribution);
    }

}
