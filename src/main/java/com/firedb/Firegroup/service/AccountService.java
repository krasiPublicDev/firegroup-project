package com.firedb.Firegroup.service;

import com.firedb.Firegroup.dto.DtoMapper;
import com.firedb.Firegroup.dto.classDto.AccountEntityInputDto;
import com.firedb.Firegroup.dto.recordDto.AccountDtoGet;
import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.exception.AccountAlreadyExistException;
import com.firedb.Firegroup.exception.AccountNotFoundException;
import com.firedb.Firegroup.exception.ContactNotFoundException;
import com.firedb.Firegroup.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ToString
public class AccountService {

    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDtoGet> getAllAccounts() {
        logger.info("Fetching all accounts.");
        return DtoMapper.INSTANCE.toAccountDtoGet(accountRepository.findAll());
    }

    public AccountDtoGet getAccountById(@NotNull Long id) {
        logger.info("Attempting to get a account entity by ID: {}", id);

        AccountEntity accountEntity = accountRepository.findFirstById(id)
                .orElseThrow(() ->
                {
                    logger.error("Account with ID: {} not found.", id); // Log before throwing exception
                    return new ContactNotFoundException(String.format("Account with ID: %d", id));
                });

        logger.info("Account with ID: {} found successfully.", id); // Log upon successful retrieval
        return DtoMapper.INSTANCE.toAccountDtoGet(accountEntity);
    }

    public AccountEntityInputDto createAccount(@NotNull AccountEntity accountEntity) {
        Long accountId = accountEntity.getAccountId();

        logger.debug("Attempting to create new account with ID: {}.", accountEntity.getAccountId());

        if (accountId != null && accountRepository.findFirstById(accountId).isPresent()) {
            logger.error("Account with ID {} already exists.", accountEntity.getAccountId());
            throw new AccountAlreadyExistException(String.format("Account with ID: %d already exists.", accountId));
        }

        AccountEntity newAccountEntity = accountRepository.save(accountEntity);
        logger.info("Account created successfully with ID: {}", accountId);

        return DtoMapper.INSTANCE.toAccountEntityInputDto(newAccountEntity);
    }

    @Transactional
    public AccountEntityInputDto updateAccount(Long accountId, ContactEntity contactEntity, String name, Integer categoryNumber) {
        logger.debug("Attempting to update account with ID: {}", accountId);

        if (accountRepository.findFirstById(accountId).isEmpty()) {
            logger.error("Account with ID {} does not exist.", accountId);
            throw new AccountNotFoundException(String.format("Account with ID: %d does not exist.", accountId));
        }

        AccountEntity updatedAccountEntity = accountRepository.alterAccount(accountId, contactEntity, name, categoryNumber)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account with ID: %d could not be updated.", accountId)));

        logger.info("Account with ID {} updated successfully.", accountId);
        return DtoMapper.INSTANCE.toAccountEntityInputDto(updatedAccountEntity);
    }

    public void deleteAccount(Long accountId) {
        logger.debug("Attempting to delete account with ID: {}", accountId);

        if (accountRepository.findFirstById(accountId).isEmpty()) {
            logger.warn("Attempted to delete a non-existent account with ID {}", accountId);
            throw new AccountNotFoundException(String.format("Could not delete account with ID: %d because it does not exist.", accountId));
        }

        logger.info("Account with ID {} deleted successfully.", accountId);
        accountRepository.deleteById(accountId);
    }


    //todo 2... fix the names of entities
    //todo 3... add get a single entity in both services
    //todo 4... add the same logic for all entities
    //todo 5... xml converter
}
