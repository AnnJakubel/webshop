package ee.annjakubel.webshop.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.annjakubel.webshop.model.database.Product;
import ee.annjakubel.webshop.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class ProductCache {

    @Autowired
    ProductRepository productRepository;

    //cachr loading otsustab, kas v6tab caches-st (ylikiire ja v2hese j6udlusega)
    //v6i v6tab andmebaasist
    private LoadingCache<Long, Product> productLoadingCache = CacheBuilder
            .newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, Product>() {
                @Override
                public Product load(Long id) throws Exception {
                    log.info("v6tan andmebaasist");
                    return productRepository.findById(id).get();
                }
            });

    //avalik funktsioon, mis v6tab cache-ist
    public Product getProduct(Long id) throws ExecutionException {
        log.info("v6tan toote");
        return productLoadingCache.get(id);
    }

    public void emptyCache() {
        productLoadingCache.invalidateAll();
    }
}
