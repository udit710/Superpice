package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.service.ProductService;
import au.edu.rmit.sept.superprice.web.SearchController;

@SpringBootTest
public class SearchControllerTest {
    
    SearchController searchController;
    ProductService productService;

    @BeforeEach
    void initializeObjects() {
        this.productService = mock(ProductService.class);
        this.searchController = new SearchController(this.productService);
    }

    @Test
    void should_return_products_by_name() {
        when(this.productService.getProductsByProductName("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts("test", null, null, null, null, null, null).size());

    }

    @Test
    void should_return_products_by_description() {
        when(this.productService.getProductsByDescription("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts(null, "test", null, null, null, null, null).size());
    }

    @Test
    void should_return_products_by_lastUpdated_date() {
        when(this.productService.getProductsByLastUpdated("01-01-2023"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts(null, null, "01-01-2023", null, null, null, null).size());
    }

    @Test
    void should_return_products_by_storeIds() {
        when(this.productService.getProductsByStoreIds(List.of(1l, 2l)))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts(null, null, null, List.of(1l, 2l), null, null, null).size());
    }

    @Test
    void should_return_products_whose_price_is_in_range() {
        when(this.productService.getProductsByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts(null, null, null, null, 1.0, 10.0, null).size());
    }

    @Test
    void should_return_products_by_allergens() {
        when(this.productService.getProductsByAllergens("test"))
            .thenReturn(List.of(new Product()));
        
        assertEquals(1, this.searchController.getAllProducts(null, null, null, null, null, null, "test").size());
    }

    @Test
    void should_return_empty_list_when_no_filters() {
        assertEquals(0, this.searchController.getAllProducts(null, null, null, null, null, null, "test").size());
    }

    @Test
    void should_return_empty_list_when_min_without_max() {
        when(this.productService.getProductsByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(0, this.searchController.getAllProducts(null, null, null, null, 1.0, null, null).size());
    }

    @Test
    void should_return_empty_list_when_max_without_min() {
        when(this.productService.getProductsByPriceBetween(1.0, 10.0))
            .thenReturn(List.of(new Product()));
        
        assertEquals(0, this.searchController.getAllProducts(null, null, null, null, null, 10.0, null).size());
    }

    @Test
    void should_return_empty_list_when_storeIds_is_empty() {
        when(this.productService.getProductsByStoreIds(List.of(1l, 2l)))
            .thenReturn(List.of(new Product()));
        
        assertEquals(0, this.searchController.getAllProducts(null, null, null, new ArrayList<Long>(), null, null, null).size());
    }

}
