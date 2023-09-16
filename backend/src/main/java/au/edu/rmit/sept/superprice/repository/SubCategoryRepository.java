package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT sc FROM SubCategory sc WHERE sc.category.categoryId = ?1")
    List<SubCategory> findAllByCategoryId(Long categoryId);

    @Query("SELECT sc FROM SubCategory sc WHERE lower(sc.category.categoryName) LIKE lower('%'||?1||'%')")
    List<SubCategory> findAllByCategoryName(String categoryName);

}
