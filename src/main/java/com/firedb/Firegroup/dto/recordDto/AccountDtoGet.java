package com.firedb.Firegroup.dto.recordDto;

import com.firedb.Firegroup.entity.AccountEntity;

public record AccountDtoGet(Long accountId, String name, Integer category_number, ContactDtoGet contactId) {

    public AccountDtoGet(AccountEntity accountEntity) {
        this(accountEntity.getAccount_id(),
                accountEntity.getName(),
                accountEntity.getCategory_number(),
                new ContactDtoGet(accountEntity.getAccount_id()));
    }
}
