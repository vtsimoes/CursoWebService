package com.victormartins.firstexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class Project {

    private Integer id;

    @NotNull(message="Name must not be null.")
    @Size(min = 3,max=150, message = "Name should ve between {min} and {max} characters.")
    private String name;

    @Email(message = "'${validatedValue}' isn't a valid e-mail.")
    private String email;

}
