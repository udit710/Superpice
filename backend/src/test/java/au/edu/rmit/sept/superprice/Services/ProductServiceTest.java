package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.repository.ProductRepository;
import au.edu.rmit.sept.superprice.service.ProductService;

@SpringBootTest
public class ProductServiceTest {
    
    ProductRepository productRepository;
    ProductService productService;

    @BeforeEach
    void init() {
        this.productRepository = mock(ProductRepository.class);
        this.productService = new ProductService(this.productRepository);
    }

    @Test
    void should_return_all_products() {
        when(this.productRepository.findAll())
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getAllProducts().size());
    }

    @Test
    void should_return_empty_list_when_no_products_exist() {
        when(this.productRepository.findAll())
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getAllProducts().size());
    }

    @Test
    void should_return_product_by_id() {
        when(this.productRepository.findById(1l))
            .thenReturn(Optional.of(new Product()));
        
        assertNotNull(this.productService.getProductById(1l));
    }

    @Test
    void should_return_null_when_no_product_with_id_exists() {
        when(this.productRepository.findById(11l))
            .thenReturn(Optional.empty());
        
        assertNull(this.productService.getProductById(11l));
    }

    @Test
    void should_save_or_update_product() {
        Product product = new Product();
        when(this.productRepository.save(product))
            .then(returnsFirstArg());

        assertEquals(product, this.productService.saveOrUpdateProduct(product));
    }

    @Test
    void should_delete_product() {
        this.productService.deleteProduct(1l);

        verify(this.productRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_return_products_by_ProductName() {
        when(this.productRepository.findByProductName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByProductName("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_ProductName() {
        when(this.productRepository.findByProductName("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByProductName("test").size());
    }

    @Test
    void should_return_products_by_Description() {
        when(this.productRepository.findByDescriptionContaining("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByDescription("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_Description() {
        when(this.productRepository.findByDescriptionContaining("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByDescription("test").size());
    }

    @Test
    void should_return_products_by_LastUpdated() {
        when(this.productRepository.findByLastUpdated("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByLastUpdated("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_LastUpdated() {
        when(this.productRepository.findByLastUpdated("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByLastUpdated("test").size());
    }

    @Test
    void should_return_products_by_Availability() {
        when(this.productRepository.findByAvailability())
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByAvailability().size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_Availability() {
        when(this.productRepository.findByAvailability())
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByAvailability().size());
    }

    @Test
    void should_return_products_by_Allergens() {
        when(this.productRepository.findByAllergensContaining("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByAllergens("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_Allergens() {
        when(this.productRepository.findByAllergensContaining("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByAllergens("test").size());
    }

    @Test
    void should_return_products_by_StoreName() {
        when(this.productRepository.findByStoreName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByStoreName("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_StoreName() {
        when(this.productRepository.findByStoreName("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByStoreName("test").size());
    }

    @Test
    void should_return_products_by_StoreIds() {
        when(this.productRepository.findByStoreIds(List.of(1l)))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByStoreIds(List.of(1l)).size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_with_StoreIds() {
        when(this.productRepository.findByStoreIds(List.of(11l)))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByStoreIds(List.of(11l)).size());
    }

    @Test
    void should_return_products_whose_price_is_in_range() {
        when(this.productRepository.findByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByPriceBetween(1.0, 10.0).size());
    }

    @Test
    void should_return_empty_list_when_max_without_min() {
        when(this.productRepository.findByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(0, this.productService.getProductsByPriceBetween(null, 10.0).size());
    }

    @Test
    void should_return_empty_list_when_min_without_max() {
        when(this.productRepository.findByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(0, this.productService.getProductsByPriceBetween(1.0, null).size());
    }

    @Test
    void should_return_products_by_CategoryId() {
        when(this.productRepository.findAllByCategoryId(1l))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByCategoryId(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_for_CategoryId() {
        when(this.productRepository.findAllByCategoryId(1l))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByCategoryId(1l).size());
    }

    @Test
    void should_return_products_by_CategoryName() {
        when(this.productRepository.findByProductName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByCategoryName("test").size());
    }

    @Test
    void should_return_empty_list_when_no_products_exists_for_CategoryName() {
        when(this.productRepository.findByProductName("test"))
            .thenReturn(new ArrayList<Product>());
        
        assertEquals(0, this.productService.getProductsByCategoryName("test").size());
    }

    @Test
    void should_return_products_by_discount() {
        when(this.productRepository.findAllByProductDetailDiscount(50))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsByProductDetailsDiscount(50).size());
    }

    @Test
    void should_return_products_with_discount() {
        when(this.productRepository.findAllThatHaveDiscount())
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.productService.getProductsThatHaveDiscount().size());
    }

}
