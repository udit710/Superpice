package au.edu.rmit.sept.superprice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY, optional = true)
    @JoinColumn(name="category_id", nullable = true)
    private Category category;

}
