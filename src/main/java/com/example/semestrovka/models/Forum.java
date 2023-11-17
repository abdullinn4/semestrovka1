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
public class Forum {
    private int id;
    private String name;
    private String title;
    private String topic;
    private String companyName;
    private Date createDate;
}
