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
    private LocalDate birth_date;
    private String physical_disability;

    public ContactEntity(String name, LocalDate birth_date, String physical_disability) {
        this.name = name;
        this.birth_date = birth_date;
        this.physical_disability = physical_disability;
    }

    //Custom getter for age
    public Integer getAge() {
        return Period.between(birth_date, LocalDate.now()).getYears();
    }
}
