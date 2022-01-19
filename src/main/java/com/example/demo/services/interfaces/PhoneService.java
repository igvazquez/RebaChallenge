package com.example.demo.services.interfaces;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.PhoneEntity;

public interface PhoneService {

    PhoneEntity updatePhone(final PhoneEntity phone, final PersonEntity person);
}
