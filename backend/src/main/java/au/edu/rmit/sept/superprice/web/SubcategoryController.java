package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.edu.rmit.sept.superprice.model.SubCategory;
import au.edu.rmit.sept.superprice.service.SubCategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/subcategory")
public class SubcategoryController {
    
    private SubCategoryService subCategoryService;

    @Autowired
    public SubcategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/{id}")
    public SubCategory getSubcategoryById(@PathVariable Long id) {
        return subCategoryService.getById(id);
    }
}
