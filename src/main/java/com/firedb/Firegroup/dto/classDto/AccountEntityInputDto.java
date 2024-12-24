package com.firedb.Firegroup.dto.classDto;

import com.firedb.Firegroup.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntityInputDto {

    private Long accountId;
    private String name;
    private Integer categoryNumber;
    private ContactEntityInputDto contactId;

    public AccountEntityInputDto(AccountEntity accountEntity) {
        this(accountEntity.getAccountId(),
                accountEntity.getName(),
                accountEntity.getCategoryNumber(),
                ContactEntityInputDto.ContactEntityInputDtoBuilder
                        .newInstance()
                        .withId(accountEntity.getContactEntity().getContactId())
                        .build());
    }


}
