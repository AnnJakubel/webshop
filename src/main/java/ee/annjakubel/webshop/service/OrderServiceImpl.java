package ee.annjakubel.webshop.service;

import ee.annjakubel.webshop.cache.ProductCache;
import ee.annjakubel.webshop.model.database.Order;
import ee.annjakubel.webshop.model.database.PaymentState;
import ee.annjakubel.webshop.model.database.Person;
import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.repository.OrderRepository;
import ee.annjakubel.webshop.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCache productCache;

    public double calculateOrderSum(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice) // map (asendab) k6ik tooted hinnaga (double kujul)
                .sum();
    }

    //Ehitan valmis Orderi ja salvestan andmebaasi
    public Long saveToDatabase(List<Product> products, double orderSum, Person person) {
        Order order = new Order();
        order.setOrderSum(orderSum);
        order.setCreationDate(new Date());//Setterid on annotationi kaudu Order klassis
        order.setProducts(products);
        order.setPaymentState(PaymentState.INITIAL);
        order.setPerson(person);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId(); //Id on Long tyypi
    }


    public List<Product> getAllProductsFromDb(List<Product> products) {
        /* List<Product> originalProducts = new ArrayList<>();
        for (Product p : products) {
            Long productId = p.getId();
            Product originalProduct = productRepository.findById(productId).get();
            originalProducts.add(originalProduct);
        }
        return originalProducts; */

        return products.stream()
                .map(p -> {
                    try {
                        return productCache.getProduct(p.getId());
                    } catch (ExecutionException e) {
                        log.error("Cache error{}", e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
