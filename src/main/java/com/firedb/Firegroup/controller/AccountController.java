package com.firedb.Firegroup.controller;

import com.firedb.Firegroup.dto.classDto.AccountEntityInputDto;
import com.firedb.Firegroup.dto.recordDto.AccountDtoGet;
import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/getAllAccounts")
    public ResponseEntity<List<AccountDtoGet>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountDtoGet> getAccountById(@PathVariable Long id) {
        AccountDtoGet accountDto = accountService.getAccountById(id);

        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @PostMapping
    public ResponseEntity<AccountEntityInputDto> createAccount(@RequestBody AccountEntity accountEntity) {
        AccountEntityInputDto accountDto = accountService.createAccount(accountEntity);
        //If service fails Global exception handler will change the Response entity.
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AccountEntityInputDto> updateAccount(@PathVariable("id") Long accountId,
                                                               @RequestParam(required = false) ContactEntity contactEntity,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) Integer categoryNumber) {
        AccountEntityInputDto updatedAccount = accountService.updateAccount(accountId, contactEntity, name, categoryNumber);

        return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();

    }

}



