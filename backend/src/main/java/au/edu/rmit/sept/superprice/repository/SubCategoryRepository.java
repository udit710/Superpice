package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    //Add query to get all the product_category  by category

}
