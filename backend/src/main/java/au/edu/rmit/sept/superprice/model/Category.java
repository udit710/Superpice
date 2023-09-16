package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<SubCategory> subCategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Product> products;
}
