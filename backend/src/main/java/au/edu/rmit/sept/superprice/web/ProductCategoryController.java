package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import au.edu.rmit.sept.superprice.service.ProductCategoryService;

@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productcategoryService;

    // TODO: Add controller methods
}
