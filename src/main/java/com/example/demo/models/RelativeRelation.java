package com.example.demo.models;

import lombok.Value;

@Value
public class RelativeRelation {

    PersonEntity person1;
    PersonEntity person2;
    String relation;
}
