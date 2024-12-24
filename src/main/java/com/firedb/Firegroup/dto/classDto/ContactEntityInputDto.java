package com.firedb.Firegroup.dto.classDto;

import com.firedb.Firegroup.entity.ContactEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ContactEntityInputDto {

    private Long contactId;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String physicalDisability;

    public ContactEntityInputDto() {

    }

    public ContactEntityInputDto(ContactEntity contactEntity) {

        this.contactId = contactEntity.getContactId();
        this.name = contactEntity.getName();
        this.birthDate = contactEntity.getBirthDate();
        this.age = contactEntity.getAge();
        this.physicalDisability = contactEntity.getPhysicalDisability();

    }

    static class ContactEntityInputDtoBuilder {
        private Long id;

        public ContactEntityInputDto build() {
            ContactEntityInputDto contactEntityInputDto = new ContactEntityInputDto();
            contactEntityInputDto.setContactId(id);
            return contactEntityInputDto;
        }

        public ContactEntityInputDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public static ContactEntityInputDtoBuilder newInstance() {
            return new ContactEntityInputDtoBuilder();
        }
    }
}
