package com.firedb.Firegroup.controller;

import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/getAllAccounts")
    public List<AccountEntity> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping(path = "/create_account")
    public ResponseEntity<Void> createAccount(@RequestBody AccountEntity accountEntity) {
        accountService.createAccount(accountEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PatchMapping(path = "/update_account/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable("id") Long accountId,
                                                @RequestParam(required = false) ContactEntity contactEntity,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) Integer categoryNumber) {
        Optional<AccountEntity> updatedAccountEntity = accountService.updateAccount(accountId, contactEntity, name, categoryNumber);

        return updatedAccountEntity
                .map(updatedAccount -> ResponseEntity.ok("Account updated successfully: " + updatedAccount))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Account not found with given ID: %d", accountId)));
    }

    @DeleteMapping(path = "/delete_account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long account_id) {
        accountService.deleteAccount(account_id);
        return ResponseEntity.noContent().build();

    }

}



