package ee.annjakubel.webshop.model.database;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long on hea andmebaasis kasutada, standartne

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private double price;

    private String imgSrc;
    private boolean active;
    private String description;
    private int stock;

    @NotNull
    @OneToOne
    private Subcategory subcategory;
}
