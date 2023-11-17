package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private int id;
    private int userId;
    private String companyName;
    private String title;
    private String contactInfo;
    private String category;
    private String country;
}
