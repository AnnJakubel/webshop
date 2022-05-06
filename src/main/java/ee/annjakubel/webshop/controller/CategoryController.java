package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.model.database.Category;
import ee.annjakubel.webshop.model.database.Subcategory;
import ee.annjakubel.webshop.repository.CategoryRepository;
import ee.annjakubel.webshop.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @GetMapping("category")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok()
                .body(categoryRepository.findAll());
    }

    @GetMapping("subcategory")
    public ResponseEntity<List<Subcategory>> getSubcategories() {
        return ResponseEntity.ok()
                .body(subcategoryRepository.findAll());
    }

    @PostMapping("category")
    public ResponseEntity<List<Category>> addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.findAll());
    }

    @PostMapping("subcategory")
    public ResponseEntity<List<Subcategory>> addCategory(@RequestBody Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subcategoryRepository.findAll());
    }
}
