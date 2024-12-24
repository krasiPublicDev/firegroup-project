package com.firedb.Firegroup.dto.recordDto;

import com.firedb.Firegroup.entity.AccountEntity;

public record AccountDtoGet(Long accountId, String name, Integer categoryNumber, ContactDtoGet contactId) {

    public AccountDtoGet(AccountEntity accountEntity) {
        this(accountEntity.getAccountId(),
                accountEntity.getName(),
                accountEntity.getCategoryNumber(),
                new ContactDtoGet.Builder().contactId(accountEntity.getContactEntity().getContactId()).build());
    }
}
