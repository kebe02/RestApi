package com.owt.challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 1, message = "Can not be less than 1")
    @Max(value = 10, message = "Can not be more than 10")
    private int level;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Contact> contacts;

}
