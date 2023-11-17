package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cooperation {
    private int id;
    private String supplierName;
    private String partnerName;
}
