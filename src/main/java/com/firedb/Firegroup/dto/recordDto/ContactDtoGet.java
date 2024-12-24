package com.firedb.Firegroup.dto.recordDto;

import com.firedb.Firegroup.entity.ContactEntity;

import java.time.LocalDate;

public record ContactDtoGet(Long contactId, String name, Integer age, LocalDate birthDate, String physicalDisability) {
    //info: there is auto generated constructor with all values
    //info: there is a constructor that takes only id


    public ContactDtoGet(ContactEntity contactEntity) {
        this(contactEntity.getContactId(),
                contactEntity.getName(),
                contactEntity.getAge(),
                contactEntity.getBirthDate(),
                contactEntity.getPhysicalDisability());
    }

    public static class Builder {
        private Long contactId;
        private String name;
        private Integer age;
        private LocalDate birthDate;
        private String physicalDisability;

        public Builder contactId(Long contactId) {
            this.contactId = contactId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder physicalDisability(String physicalDisability) {
            this.physicalDisability = physicalDisability;
            return this;
        }

        public ContactDtoGet build() {
            return new ContactDtoGet(contactId, name, age, birthDate, physicalDisability);
        }
    }
}
