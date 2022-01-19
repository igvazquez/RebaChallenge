package com.example.demo.controllers;

import com.example.demo.api.PhonesApi;
import com.example.demo.converters.PhoneConverter;
import com.example.demo.models.Phone;
import com.example.demo.services.interfaces.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneController implements PhonesApi {

    private final PhoneService phoneService;

    @Override
    public ResponseEntity<Phone> getPhoneById(final Long phoneId) {
        var phone = phoneService.getPhoneById(phoneId)
                .map(PhoneConverter::convertToPhoneDto);

        return phone
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Phone>> getPhones() {
        var phones = phoneService.getAllPhones();

        return ResponseEntity.ok(phones.stream().map(PhoneConverter::convertToPhoneDto)
                .collect(Collectors.toList()));
    }
}
