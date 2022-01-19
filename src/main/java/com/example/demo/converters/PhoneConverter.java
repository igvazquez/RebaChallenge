package com.example.demo.converters;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.Phone;
import com.example.demo.models.PhoneEntity;

public final class PhoneConverter {

    private PhoneConverter(){

    }


    public static Phone convertToPhoneDto(final PhoneEntity phone) {
        return new Phone()
                .id(phone.getId())
                .number(phone.getNumber());
    }

    public static PhoneEntity convertToPhoneEntity(final Phone phone, final PersonEntity person) {
        return PhoneEntity.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .person(person)
                .build();
    }
}
