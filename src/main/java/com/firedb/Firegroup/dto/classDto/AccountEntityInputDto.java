package com.firedb.Firegroup.dto.classDto;

import com.firedb.Firegroup.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountEntityInputDto {

    private Long accountId;
    private String name;
    private Integer categoryNumber;
    private ContactEntityInputDto contactId;

    public AccountEntityInputDto(AccountEntity accountEntity) {
        this(accountEntity.getAccountId(),
                accountEntity.getName(),
                accountEntity.getCategoryNumber(),
                new ContactEntityInputDto(accountEntity.getAccountId()));
    }


}
