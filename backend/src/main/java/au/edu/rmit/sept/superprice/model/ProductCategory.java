package au.edu.rmit.sept.superprice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PRODUCT_CATEGORY")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_category_id")
    private Long productCategoryId;

    @Column(name="product_category_name")
    private String productCategoryName;

    @OneToMany(mappedBy= "productCategory", cascade=CascadeType.ALL,  fetch = FetchType.LAZY)
    private Collection<Product> productIds;

    //Add relationship with Category Entity here
//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;

}
