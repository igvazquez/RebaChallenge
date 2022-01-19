package com.example.demo.controllers;

import com.example.demo.api.AddressesApi;
import com.example.demo.converters.AddressConverter;
import com.example.demo.models.Address;
import com.example.demo.services.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController implements AddressesApi {

    private final AddressService addressService;

    @Override
    public ResponseEntity<Address> getAddressById(final Long addressId) {
        var address = addressService.getAddressById(addressId)
                .map(AddressConverter::convertToAddressDto);

        return address
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());    }

    @Override
    public ResponseEntity<List<Address>> getAddresses() {
        var addresses = addressService.getAllAddresses();

        return ResponseEntity.ok(addresses.stream().map(AddressConverter::convertToAddressDto)
                .collect(Collectors.toList()));
    }
}
