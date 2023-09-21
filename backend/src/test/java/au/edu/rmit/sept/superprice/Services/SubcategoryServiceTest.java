package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.SubCategory;
import au.edu.rmit.sept.superprice.repository.SubCategoryRepository;
import au.edu.rmit.sept.superprice.service.SubCategoryService;

@SpringBootTest
public class SubcategoryServiceTest {
    
    SubCategoryService subCategoryService;
    SubCategoryRepository subCategoryRepository;

    @BeforeEach
    void init() {
        this.subCategoryRepository = mock(SubCategoryRepository.class);
        this.subCategoryService = new SubCategoryService(this.subCategoryRepository);
    }

    @Test
    void should_return_subcategory_by_id() {
        when(this.subCategoryRepository.findById(1l))
            .thenReturn(Optional.of(new SubCategory()));
        
        assertNotNull(this.subCategoryService.getById(1l));
    }

    @Test
    void should_return_null_when_no_subcategory_with_id_exists() {
        when(this.subCategoryRepository.findById(11l))
            .thenReturn(Optional.empty());
        
        assertNull(this.subCategoryService.getById(11l));
    }

}
