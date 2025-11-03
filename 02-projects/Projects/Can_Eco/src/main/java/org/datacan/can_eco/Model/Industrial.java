package org.datacan.can_eco.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "industrial")


public class Industrial {

    @Id
    @GeneratedValue
    @Column(name = "industrial_id", nullable = false)
    private UUID id;

    @NotEmpty(message = "Name can't be empty.")
    private String name;

    @NotNull(message = "Price can't be null.")
    private Integer price;

    private String description;

    private String pictureLink;

    @NotNull(message = "Poisonous must be specified.")
    private Boolean poisonous;

    @NotNull(message = "Volume can't be null.")
    @DecimalMin(value = "0.01", message = "Volume must be greater than 0.")
    private Double volume;

    @NotNull(message = "A industrial product has to have a category")
    private String category;


}
