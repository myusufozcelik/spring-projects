package com.ozcelik.entity;

import lombok.*;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"}) // if two id values are equals then
@ToString
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_person_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_person_address", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 500, name = "address")
    private String address;
    @Enumerated
    private AddressType addressType;
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_address_id")
    private Person person;

    public enum AddressType {
        HOME,
        WORK,
        OTHER
    }
}
