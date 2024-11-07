package com.firedb.Firegroup.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ContactEntity {

    @Id
    @SequenceGenerator(
            name = "contact_id_seq",
            sequenceName = "contact_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_id_seq"
    )
    private Long id;
    private String name;

    @Transient
    private Integer age;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "physical_disability")
    private String physicalDisability;

    public ContactEntity(String name, LocalDate birthDate, String physicalDisability) {
        this.name = name;
        this.birthDate = birthDate;
        this.physicalDisability = physicalDisability;
    }

    //Custom getter for age
    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
