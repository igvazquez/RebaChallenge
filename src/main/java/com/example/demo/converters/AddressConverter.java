package com.example.demo.converters;

import com.example.demo.models.Address;
import com.example.demo.models.AddressEntity;
import com.example.demo.models.PersonEntity;

public final class AddressConverter {

    private AddressConverter() {
        // Utility class
    }

    public static Address convertToAddressDto(final AddressEntity address) {
        return new Address()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .appartment(address.getApartment());
    }

    public static AddressEntity convertToAddressEntity(final Address address, final PersonEntity person) {
        return AddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .apartment(address.isAppartment())
                .person(person)
                .build();
    }
}
