package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.PhoneEntity;
import com.example.demo.repositories.PhoneRepository;
import com.example.demo.services.interfaces.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<PhoneEntity> getPhoneByUserId(final Long userId) {
        return phoneRepository.findPhoneByPersonId(userId);
    }

    @Override
    public Optional<PhoneEntity> getPhoneById(Long phoneId) {
        return phoneRepository.findById(phoneId);
    }

    @Override
    public List<PhoneEntity> getAllPhones() {
        return phoneRepository.findAll();
    }
}
