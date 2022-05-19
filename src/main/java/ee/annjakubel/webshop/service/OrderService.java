package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.database.Person;
import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.model.request.input.CartProducts;

import java.util.List;

public interface OrderService {
    double calculateOrderSum(List<Product> products);

    Long saveToDatabase(List<Product> products, double orderSum, Person person);

    List<Product> getAllProductsFromDb(List<Product> products);
}
