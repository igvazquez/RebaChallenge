package com.example.demo.services.interfaces;

import com.example.demo.models.AddressEntity;
import com.example.demo.models.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    AddressEntity updateAddress(final AddressEntity address, final PersonEntity person);

    Optional<AddressEntity> getAddressByUserId(final Long userId);

    Optional<AddressEntity> getAddressById(final Long addressId);

    List<AddressEntity> getAllAddresses();
}
