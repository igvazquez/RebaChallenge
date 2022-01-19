package com.example.demo.services.interfaces;

import com.example.demo.models.AddressEntity;
import com.example.demo.models.PersonEntity;

public interface AddressService {

    AddressEntity updateAddress(final AddressEntity address, final PersonEntity person);
}
