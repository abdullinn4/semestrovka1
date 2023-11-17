package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private int forumId;
    private String sender;
    private String text;
    private String timestamp;
}
