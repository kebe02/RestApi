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
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotEmpty(message = "Lastname is mandatory")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @NotEmpty(message = "Fullname is mandatory")
    @NotBlank(message = "Fullname is mandatory")
    private String fullname;

    @NotEmpty(message = "Address is mandatory")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Email(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile is mandatory")
    private String mobile;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skill> skills;

}
