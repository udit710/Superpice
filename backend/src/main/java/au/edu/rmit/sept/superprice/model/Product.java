package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String description;
    private Double price;
    private Boolean availability;
    private String imageURL;
    private String lastUpdated;

    // Constructors, getters, setters, etc.
    public Product() {
    }

    public Product(String productName, String description, Double price, Boolean availability, String imageURL, String lastUpdated) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.imageURL = imageURL;
        this.lastUpdated = lastUpdated;
    }

    public Product(Long id, String productName, String description, Double price, Boolean availability, String imageURL, String lastUpdated) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.imageURL = imageURL;
        this.lastUpdated = lastUpdated;
    }

}
