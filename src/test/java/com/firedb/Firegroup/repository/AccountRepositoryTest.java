package com.firedb.Firegroup.repository;

import com.firedb.Firegroup.entity.AccountEntity;
import com.firedb.Firegroup.entity.ContactEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindFirstAccountById() {
        //given
        AccountEntity account = new AccountEntity(
                1L,
                "Jamal",
                22,
                new ContactEntity("John", LocalDate.now(), "none")
        );
        underTest.save(account);

        //when
        Optional<AccountEntity> exists = underTest.findFirstById(account.getAccountId());

        //then
        assertThat(exists)
                .isPresent()
                .contains(account);
    }

    @Test
    void itShouldNotFindFirstAccountById() {
        //given
        Long accountId = 1L;

        //when
        Optional<AccountEntity> exists = underTest.findFirstById(accountId);

        //then
        assertThat(exists)
                .isNotPresent();
    }

}