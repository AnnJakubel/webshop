package ee.annjakubel.webshop.model.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id; //long on hea andmebaasis kasutada, standartne
    private String name;
    private double price;
    private String imgSrc;
    private boolean active;
    private String desctiption;
    private String category;

}
