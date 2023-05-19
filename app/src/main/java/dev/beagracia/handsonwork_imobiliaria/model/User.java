package dev.beagracia.handsonwork_imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um usuario no sistema.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePicPath;
}
