package com.firedb.Firegroup.service;

import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.exception.AccountAlreadyExistException;
import com.firedb.Firegroup.exception.AccountNotFoundException;
import com.firedb.Firegroup.repository.AccountRepository;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ToString
public class AccountService {

    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountEntity> getAllAccounts() {
        logger.info("Fetching all accounts.");
        return accountRepository.findAll();
    }

    public void createAccount(AccountEntity accountEntity) {
        Long accountId = accountEntity.getAccount_id();

        logger.debug("Attempting to create new account with ID: {}.", accountEntity.getAccount_id());

        if (accountId != null && accountRepository.findFirstById(accountId).isPresent()) {
            logger.error("Account with ID {} already exists.", accountEntity.getAccount_id());
            throw new AccountAlreadyExistException(String.format("Account with ID: %d already exists.", accountId));
        }

        logger.info("Account created successfully with ID: {}", accountId);
        accountRepository.save(accountEntity);
    }

    public Optional<AccountEntity> updateAccount(Long accountId, ContactEntity contactEntity, String name, Integer categoryNumber) {
        logger.debug("Attempting to update account with ID: {}", accountId);

        if (accountRepository.findFirstById(accountId).isEmpty()) {
            logger.error("Account with ID {} does not exist.", accountId);
            throw new AccountNotFoundException(String.format("Account with ID: %d does not exist.", accountId));
        }
        Optional<AccountEntity> updatedAccountEntity = accountRepository.alterAccount(accountId, contactEntity, name, categoryNumber);

        logger.info("Account with ID {} updated successfully.", accountId);
        return updatedAccountEntity;
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

    //todo 2... ad to github and fix name
    //todo do trough sql query's so you don't forget them
    //todo 3... check what are java spring records and implement them
    //todo 4... add the same logic for all entities
    //todo ask chat for better stuff add ge for one and do dto or other ----- done = false
}
