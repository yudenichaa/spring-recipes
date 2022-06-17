package dev.nightzen.recipes.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auth_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column
    private Long userId;

    @Column
    @NotBlank
    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;

    @Column
    @NotBlank
    @Size(min = 8)
    private String password;
}
