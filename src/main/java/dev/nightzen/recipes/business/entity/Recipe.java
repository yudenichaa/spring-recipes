package dev.nightzen.recipes.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String description;

    @Column
    @NotBlank
    private String category;

    @Column
    @UpdateTimestamp
    private LocalDateTime date;

    @Column
    @Size(min = 1)
    @NotEmpty
    private String[] ingredients;

    @Column
    @Size(min = 1)
    @NotEmpty
    private String[] directions;

    @OneToOne
    @JoinColumn(name="userId", nullable = false)
    @JsonIgnore
    private User user;
}
