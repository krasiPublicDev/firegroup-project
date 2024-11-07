package com.firedb.Firegroup.dto.classDto;

import com.firedb.Firegroup.entity.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntityInputDto {

    private Long contactId;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String physicalDisability;

    public static ContactEntityInputDto byId(Long contactId) {
        return new ContactEntityInputDto(contactId,
                null,
                null,
                null,
                null);
    }

    public ContactEntityInputDto(ContactEntity contactEntity) {
        this(contactEntity.getId(),
                contactEntity.getName(),
                contactEntity.getAge(),
                contactEntity.getBirthDate(),
                contactEntity.getPhysicalDisability());
    }
}
