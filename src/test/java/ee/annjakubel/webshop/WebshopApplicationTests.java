package ee.annjakubel.webshop;

import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebshopApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void checkIfCanGetProducts() {
       List<Product> products =  productRepository.findAll();
        Assertions.assertEquals(true, products.size() > 5000);
    }

    @Test
    void checkIfProductAddedToRepository() {
        Product addedProduct = new Product(99L, "Nimi", 10.00, null, true, null, 10, null);
        Product savedProduct  = productRepository.save(addedProduct);
        Product fetchedProduct = productRepository.findById(savedProduct.getId()).get();
        Assertions.assertEquals(addedProduct.getName(), fetchedProduct.getName());
    }

}
