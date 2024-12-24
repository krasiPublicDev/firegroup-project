package com.firedb.Firegroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;


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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AccountEntity that = (AccountEntity) o;
        return getAccountId() != null && Objects.equals(getAccountId(), that.getAccountId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
