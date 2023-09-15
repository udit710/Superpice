package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SUB_CATEGORY")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sub_category_id")
    private Long subCategoryId;

    @Column(name="sub_category_name")
    private String subCategoryName;

    @OneToMany(mappedBy= "subCategory", cascade=CascadeType.ALL,  fetch = FetchType.LAZY)
    private Collection<Product> productIds;

    //Add relationship with Category Entity here
//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;

}
