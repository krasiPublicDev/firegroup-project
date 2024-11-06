package com.firedb.Firegroup.dto.classDto;

import com.firedb.Firegroup.entity.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ContactEntityInputDto {

    private Long contactId;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String physical_disability;

    public ContactEntityInputDto(Long contactId) {
        this(contactId,
                null,
                null,
                null,
                null);
    }

    public ContactEntityInputDto(ContactEntity contactEntity) {
        this(contactEntity.getId(),
                contactEntity.getName(),
                contactEntity.getAge(),
                contactEntity.getBirth_date(),
                contactEntity.getPhysical_disability());
    }
}
