package com.ozcelik.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<String> address;
}
