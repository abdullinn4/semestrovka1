package com.example.semestrovka.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Partnership {
    private int id;
    private String initiator;
    private String partner;
    private String status;
    private Date startDate;
    private Date endDate;
    private String title;
}
