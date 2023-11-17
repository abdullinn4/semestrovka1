package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    private int id;
    private String investorName;
    private String recipientName;
    private String amount;
    private String status;
    private Date investmentDate;
    private String title;
}
