package com.firedb.Firegroup.repository;

import com.firedb.Firegroup.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    @Query("SELECT ce FROM ContactEntity ce WHERE ce.id = ?1")
    Optional<ContactEntity> findFirstById(Long id);

    @Modifying
    @Query("UPDATE ContactEntity ce SET ce.name = ?2, ce.birthDate = ?3, ce.physicalDisability = ?4 WHERE ce.id = ?1")
    Optional<ContactEntity> updateContact(Long id, String name, LocalDate birth_date, String physical_disability);

}
