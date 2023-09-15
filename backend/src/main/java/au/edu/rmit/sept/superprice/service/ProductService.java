package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public List<Product> getProductsByDescription(String description) {
        return productRepository.findByDescriptionContaining(description);
    }

    public List<Product> getProductsByLastUpdated(String lastUpdated) {
        return productRepository.findByLastUpdated(lastUpdated);
    }

    public List<Product> getProductsByAvailability() {
        return productRepository.findByAvailability();
    }

    public List<Product> getProductsByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsByAllergens(String allergen) {
        return productRepository.findByAllergensContaining(allergen);
    }

    public List<Product> getProductsByStoreName(String storeName) {
        return productRepository.findByStoreName(storeName);
    }

    public List<Product> getProductsByStoreIds(List<Long> storeIds) {
        return productRepository.findByStoreIds(storeIds);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsBySubCategoryId(Long subCategory){
        return productRepository.findAllBySubCategoryId(subCategory);
    }

    public List<Product> getProductsBySubCategoryName(String subCategoryName){
        return productRepository.findAllBySubCategoryName(subCategoryName);
    }

    public List<Product> getProductsByCategoryId(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> getProductsByCategoryName(String categoryName){
        return productRepository.findByProductName(categoryName);
    }
}
