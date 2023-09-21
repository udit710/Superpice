package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.SubCategory;
import au.edu.rmit.sept.superprice.service.SubCategoryService;
import au.edu.rmit.sept.superprice.web.SubcategoryController;

@SpringBootTest
public class SubcategoryControllerTest {
    SubcategoryController subcategoryController;
    SubCategoryService subCategoryService;

    @BeforeEach
    void initializeObjects() {
        this.subCategoryService = mock(SubCategoryService.class);
        this.subcategoryController = new SubcategoryController(this.subCategoryService);
    }

    @Test
    void should_return_subcategory_by_id() {
        when(this.subCategoryService.getById(1l))
            .thenReturn(new SubCategory());
        
        assertNotNull(this.subcategoryController.getSubcategoryById(1l));
    }

    @Test
    void should_return_null_when_no_subcategory_by_id() {
        when(this.subCategoryService.getById(11l))
            .thenReturn(null);
        
        assertNull(this.subcategoryController.getSubcategoryById(11l));
    }

}
