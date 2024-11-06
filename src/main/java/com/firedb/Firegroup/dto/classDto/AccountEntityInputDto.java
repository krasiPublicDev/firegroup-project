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
    private Integer category_number;
    private ContactEntityInputDto contactId;

    public AccountEntityInputDto(AccountEntity accountEntity) {
        this(accountEntity.getAccount_id(),
                accountEntity.getName(),
                accountEntity.getCategory_number(),
                new ContactEntityInputDto(accountEntity.getAccount_id()));
    }


}
