package com.example.demo.services.interfaces;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.Phone;
import com.example.demo.models.PhoneEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PhoneService {

    PhoneEntity updatePhone(final PhoneEntity phone, final PersonEntity person);

    Optional<PhoneEntity> getPhoneByUserId(final Long userId);

    Optional<PhoneEntity> getPhoneById(final Long phoneId);

    List<PhoneEntity> getAllPhones();
}
