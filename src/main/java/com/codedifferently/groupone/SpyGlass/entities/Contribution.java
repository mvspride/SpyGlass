package com.codedifferently.groupone.SpyGlass.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contribution {
    @Id
    @GeneratedValue
    private Long id;
    private Date contributionDate;
    private double amount;
    @JsonIgnore
    @ManyToOne
    private Goal goal;

}
