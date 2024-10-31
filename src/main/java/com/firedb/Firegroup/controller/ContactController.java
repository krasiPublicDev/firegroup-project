package com.firedb.Firegroup.controller;

import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.service.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public List<ContactEntity> getALLContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping(path = "/create_contact")
    public void createContact(@RequestBody ContactEntity contactToCreate) {
        contactService.createContact(contactToCreate);
    }

    @Transactional
    @PatchMapping(path = "/update_contact/{id}")
    public void updateContact(@PathVariable("id") ContactEntity contactEntity,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) LocalDate birth_date,
                              @RequestParam(required = false) String physical_disability) {
        contactService.updateContact(contactEntity, name, birth_date, physical_disability);
    }

    @DeleteMapping(path = "/delete_contact/{id}")
    public void deleteContact(@PathVariable("id") ContactEntity contactToDelete) {
        contactService.deleteContact(contactToDelete);
    }

}
