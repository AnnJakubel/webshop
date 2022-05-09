package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.model.database.Product;

import java.util.List;

public interface OrderService {
    double calculateOrderSum(List<Product> products);

    Long saveToDatabase(List<Product> products, double orderSum);

    List<Product> getAllProductsFromDb(List<Product> products);
}
