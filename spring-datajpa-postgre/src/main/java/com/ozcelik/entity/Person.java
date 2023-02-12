package com.ozcelik.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString
public class Person implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_person", allocationSize = 1)
    @GeneratedValue(generator = "seq_person", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 75, name = "first_name")
    private String firstName;
    @Column(length = 100, name = "last_name")
    private String lastName;

    @OneToMany
    @JoinColumn(name = "person_address_id")
    private List<Address> address;


}
