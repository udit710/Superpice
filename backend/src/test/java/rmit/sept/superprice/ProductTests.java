package rmit.sept.superprice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rmit.sept.superprice.model.Product;
import rmit.sept.superprice.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        product = productService.saveOrUpdateProduct(product);
        assertNotNull(product);
    }
}
