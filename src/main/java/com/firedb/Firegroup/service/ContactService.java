package com.firedb.Firegroup.service;

import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.repository.ContactRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactEntity> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<ContactEntity> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public void createContact(@NotNull ContactEntity contactToCreate) throws IllegalArgumentException {
        Long contactId = contactToCreate.getId();

        if (contactId != null && contactRepository.findFirstById(contactToCreate.getId()).isPresent()) {
            throw new IllegalArgumentException(String.format("Contact with id: %d already exists.", contactId));
        }

        contactRepository.save(contactToCreate);
    }

    public void updateContact(ContactEntity existingContact, String name, LocalDate birthDate, String physicalDisability) {
        checkForContactThrowIfNotExist(existingContact);
        contactRepository.updateContact(existingContact.getId(), name, birthDate, physicalDisability);
    }

    public void deleteContact(ContactEntity contactToDelete) {
        checkForContactThrowIfNotExist(contactToDelete);
        contactRepository.deleteById(contactToDelete.getId());
    }

    private void checkForContactThrowIfNotExist(ContactEntity existingContact) {
        if (existingContact == null || existingContact.getId() == null ||
                contactRepository.findFirstById(existingContact.getId()).isEmpty()) {
            throw new IllegalArgumentException("Contact doesn't exist.");
        }
    }
}
