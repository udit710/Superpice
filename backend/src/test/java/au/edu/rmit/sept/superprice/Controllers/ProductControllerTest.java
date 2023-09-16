package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.service.ProductService;
import au.edu.rmit.sept.superprice.web.ProductController;

@SpringBootTest
public class ProductControllerTest {
    
    ProductController productController;
    ProductService productService;

    @BeforeEach
    void initializeObjects() {
        this.productService = mock(ProductService.class);
        this.productController = new ProductController(this.productService);
    }

    @Test
    void should_get_all_products() {
        when(this.productService.getAllProducts())
            .thenReturn(List.of(new Product(), new Product()));
        
        assertEquals(2, this.productController.getAllProducts().size());
    }

    @Test
    void should_return_empty_when_noProduct() {
        when(this.productService.getAllProducts())
            .thenReturn(new ArrayList<Product>());

        assertEquals(0, this.productController.getAllProducts().size());
    }

    @Test
    void should_return_product_by_id() {
        Product product = new Product();
        when(this.productService.getProductById(1l))
            .thenReturn(product);
        
        assertEquals(product, this.productController.getProductById(1l));
    }

    @Test
    void should_return_null_when_no_product_by_id() {
        when(this.productService.getProductById(11l))
            .thenReturn(null);
        
        assertNull(this.productController.getProductById(11l));
    }

    @Test
    void should_create_product() {
        Product product = new Product();
        when(this.productService.saveOrUpdateProduct(product))
            .then(returnsFirstArg());

        assertEquals(product, this.productController.createProduct(product));
    }

    @Test
    void should_update_product() {
        Product product = new Product();
        when(this.productService.saveOrUpdateProduct(product))
            .then(returnsFirstArg());

        assertEquals(product, this.productController.updateProduct(1l, product));
    }

    @Test
    void should_delete_product() {
        this.productController.deleteProduct(1l);

        verify(this.productService, times(1))
            .deleteProduct(1l);
    }

    @Test
    void should_get_products_by_subcategoryId() {
        when(this.productService.getProductsBySubCategoryId(1l))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productController.getProductsByProductCategoryId(1l).size());
    }

    @Test
    void should_get_empty_list_if_subcategoryId_not_exists() {
        when(this.productService.getProductsBySubCategoryId(11l))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productController.getProductsByProductCategoryId(11l).size());
    }

    @Test
    void should_get_products_by_subcategoryName() {
        when(this.productService.getProductsBySubCategoryName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productController.getProductsByProductCategoryName("test").size());
    }

    @Test
    void should_get_empty_list_if_subcategoryName_not_exists() {
        when(this.productService.getProductsBySubCategoryName("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productController.getProductsByProductCategoryName("test").size());
    }

    @Test
    void should_get_products_by_categoryId() {
        when(this.productService.getProductsByCategoryId(1l))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productController.getProductsByCategoryId(1l).size());
    }

    @Test
    void should_get_empty_list_if_categoryId_not_exists() {
        when(this.productService.getProductsByCategoryId(11l))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productController.getProductsByCategoryId(11l).size());
    }

    @Test
    void should_get_products_by_categoryName() {
        when(this.productService.getProductsByCategoryName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productController.getProductsByCategoryName("test").size());
    }

    @Test
    void should_get_empty_list_if_categoryName_not_exists() {
        when(this.productService.getProductsByCategoryName("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productController.getProductsByCategoryName("test").size());
    }
}
