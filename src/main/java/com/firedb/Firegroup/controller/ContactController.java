package com.firedb.Firegroup.controller;

import com.firedb.Firegroup.dto.classDto.ContactEntityInputDto;
import com.firedb.Firegroup.dto.recordDto.ContactDtoGet;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.service.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/getAllContacts")
    public ResponseEntity<List<ContactDtoGet>> getALLContacts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contactService.getAllContacts());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactDtoGet> getContactById(@PathVariable Long id) {
        ContactDtoGet contactDto = contactService.getContactById(id);

        return ResponseEntity.status(HttpStatus.OK).body(contactDto);
    }

    @PostMapping
    public ResponseEntity<ContactEntityInputDto> createContact(@RequestBody ContactEntity contactToCreate) {
        ContactEntityInputDto contactDto = contactService.createContact(contactToCreate);

        return ResponseEntity.status(HttpStatus.CREATED).body(contactDto);
    }

    @Transactional
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ContactEntityInputDto> updateContact(@PathVariable("id") Long contactId,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) LocalDate birthDate,
                                                               @RequestParam(required = false) String physicalDisability) {
        ContactEntityInputDto updateContact = contactService.updateContact(contactId, name, birthDate, physicalDisability);
        return ResponseEntity.status(HttpStatus.OK).body(updateContact);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }

}
