package com.firedb.Firegroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Setter
@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountEntity {

    @Id
    @SequenceGenerator(
            name = "account_account_id_seq",
            sequenceName = "account_account_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_account_id_seq"
    )

    @Column(name = "account_id")
    private Long accountId;

    private String name;

    @Column(name = "category_number")
    private Integer categoryNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private ContactEntity contactEntity;

    public AccountEntity(String name, Integer categoryNumber, ContactEntity contactEntity) {
        this.name = name;
        this.categoryNumber = categoryNumber;
        this.contactEntity = contactEntity;
    }
}
