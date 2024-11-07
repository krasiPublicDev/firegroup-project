package com.firedb.Firegroup.dto;

import com.firedb.Firegroup.dto.classDto.AccountEntityInputDto;
import com.firedb.Firegroup.dto.classDto.ContactEntityInputDto;
import com.firedb.Firegroup.dto.recordDto.AccountDtoGet;
import com.firedb.Firegroup.dto.recordDto.ContactDtoGet;
import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    AccountEntityInputDto toAccountEntityInputDto(AccountEntity accountEntity);

    AccountDtoGet toAccountDtoGet(AccountEntity accountEntity);

    List<AccountDtoGet> toAccountDtoGet(List<AccountEntity> accountEntity);

    ContactEntityInputDto toContactEntityInputDto(ContactEntity contactEntity);

    ContactDtoGet toContactDtoGet(ContactEntity contactEntity);

    List<ContactDtoGet> toContactDtoGet(List<ContactEntity> contactEntity);


}
