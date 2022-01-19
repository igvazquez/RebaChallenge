package com.example.demo.services;

import com.example.demo.models.AddressEntity;
import com.example.demo.models.PersonEntity;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.services.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressEntity updateAddress(AddressEntity address, PersonEntity person) {
        var oldAddress = addressRepository.findAddressByPersonId(person.getId());

        address.setPerson(person);

        var addressToSave = oldAddress.map( a -> {
            a.setStreet(address.getStreet());
            a.setNumber(address.getNumber());
            a.setApartment(address.getApartment() != null && address.getApartment());
            return a;
        }).orElse(address);

        return addressRepository.save(addressToSave);
    }
}
