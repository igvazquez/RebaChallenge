package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.PhoneEntity;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.services.interfaces.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    public PhoneEntity updatePhone(final PhoneEntity phone, final PersonEntity person) {
        var oldPhone = phoneRepository.findPhoneByPersonId(person.getId());

        phone.setPerson(person);

        var phoneToSave = oldPhone.map( p -> {
            p.setNumber(phone.getNumber());
            return p;
        }).orElse(phone);

        return phoneRepository.save(phoneToSave);
    }
}
