package com.firedb.Firegroup.service;

import com.firedb.Firegroup.dto.DtoMapper;
import com.firedb.Firegroup.dto.classDto.ContactEntityInputDto;
import com.firedb.Firegroup.dto.recordDto.ContactDtoGet;
import com.firedb.Firegroup.entity.ContactEntity;
import com.firedb.Firegroup.exception.AccountAlreadyExistException;
import com.firedb.Firegroup.exception.ContactNotFoundException;
import com.firedb.Firegroup.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@ToString
public class ContactService {

    private final ContactRepository contactRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDtoGet> getAllContacts() {
        logger.info("Fetching all contacts.");
        return DtoMapper.INSTANCE.toContactDtoGet(contactRepository.findAll());
    }

    public ContactDtoGet getContactById(@NotNull Long id) {
        logger.info("Attempting to get a contact entity by ID: {}", id);

        ContactEntity contactEntity = contactRepository.findFirstById(id)
                .orElseThrow(() ->
                {
                    logger.error("Contact with ID: {} not found.", id); // Log before throwing exception
                    return new ContactNotFoundException(String.format("Contact with ID: %d", id));
                });

        logger.info("Contact with ID: {} found successfully.", id); // Log upon successful retrieval
        return DtoMapper.INSTANCE.toContactDtoGet(contactEntity);
    }

    public ContactEntityInputDto createContact(@NotNull ContactEntity contactEntity) {
        Long contactId = contactEntity.getContactId();

        logger.debug("Attempting to create a new contact with ID: {}", contactId);

        if (contactId != null && contactRepository.findFirstById(contactId).isPresent()) {
            logger.error("Contact with ID: {} already exists.", contactId);
            throw new AccountAlreadyExistException(String.format("Contact with id: %d already exists.", contactId));
        }

        ContactEntity newContactEntity = contactRepository.save(contactEntity);
        logger.debug("Contact created successfully with ID: {}", contactId);

        return DtoMapper.INSTANCE.toContactEntityInputDto(newContactEntity);
    }

    @Transactional
    public ContactEntityInputDto updateContact(Long contactId, String name, LocalDate birthDate, String physicalDisability) {
        logger.info("Attempting to update contact with ID: {}", contactId);

        if (contactRepository.findFirstById(contactId).isEmpty()) {
            logger.error("Contact with ID: {} does not exist.", contactId);
            throw new ContactNotFoundException(String.format("Contact with ID: %d does not exist.", contactId));
        }

        ContactEntity newContactEntity = contactRepository.updateContact(contactId, name, birthDate, physicalDisability)
                .orElseThrow(() -> new ContactNotFoundException(String.format("Contact with ID: %d could not be updated.", contactId)));

        logger.info("Contact with ID: {} was created successfully", contactId);
        return DtoMapper.INSTANCE.toContactEntityInputDto(newContactEntity);
    }

    public void deleteContact(Long contactId) {
        logger.info("Attempting to delete contact with ID: {}", contactId);
        if (contactRepository.findFirstById(contactId).isEmpty()) {
            logger.error("Could not delete contact ID: {} because it dos not exist.", contactId);
            throw new ContactNotFoundException(String.format("Could not delete contact ID: %d because it dos not exist.", contactId));
        }
        contactRepository.deleteById(contactId);
    }

}
