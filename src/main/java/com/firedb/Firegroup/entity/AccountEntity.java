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
    private Long account_id;
    private String name;
    private Integer category_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private ContactEntity contactEntity;

    public AccountEntity(String name, Integer category_number, ContactEntity contactEntity) {
        this.name = name;
        this.category_number = category_number;
        this.contactEntity = contactEntity;
    }
}
