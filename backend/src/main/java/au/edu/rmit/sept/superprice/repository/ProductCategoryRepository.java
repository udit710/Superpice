package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    //Add query to get all the product_category  by category

}
