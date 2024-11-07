package com.firedb.Firegroup.repository;


import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT at FROM AccountEntity at WHERE at.id  = ?1")
    Optional<AccountEntity> findFirstById(Long id);


    @Modifying
    @Query("UPDATE AccountEntity at SET at.contactEntity = ?2, at.name = ?3, at.categoryNumber = ?4 WHERE at.accountId = ?1")
    Optional<AccountEntity> alterAccount(Long account_id, ContactEntity contactEntity, String name, Integer category_number);
}
