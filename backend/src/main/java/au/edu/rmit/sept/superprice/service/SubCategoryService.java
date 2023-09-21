package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.rmit.sept.superprice.model.SubCategory;
import au.edu.rmit.sept.superprice.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
    
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public SubCategory getById(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }
}
