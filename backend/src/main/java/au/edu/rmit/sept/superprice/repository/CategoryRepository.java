package au.edu.rmit.sept.superprice.repository;


import au.edu.rmit.sept.superprice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //String GetCategoryNameByCategoryId(Long categoryId);

}
