package com.firedb.Firegroup.dto.recordDto;

import com.firedb.Firegroup.entity.ContactEntity;

import java.time.LocalDate;

public record ContactDtoGet(Long contactId, String name, Integer age, LocalDate birthDate, String physicalDisability) {
    //info: there is auto generated constructor with all values
    //info: there is a constructor that takes only id
    public ContactDtoGet(Long contactId) {
        this(contactId,
                null,
                null,
                null,
                null);
    }

    public ContactDtoGet(ContactEntity contactEntity) {
        this(contactEntity.getId(),
                contactEntity.getName(),
                contactEntity.getAge(),
                contactEntity.getBirthDate(),
                contactEntity.getPhysicalDisability());
    }
}
