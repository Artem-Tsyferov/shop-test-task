package ua.tsyferov.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.tsyferov.shop.exception.ResourceNotFoundException;
import ua.tsyferov.shop.model.Product;
import ua.tsyferov.shop.repository.ProductRepository;
import ua.tsyferov.shop.service.ProductService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> getProductsByNameNotMatchRegexp(String regexp) {

        List<Product> products = productRepository.findAll();

        List<Product> filteredProducts = filterResult(regexp, products);

        if (filteredProducts.isEmpty()) {
            throw new ResourceNotFoundException("Products not found");
        }
        return filteredProducts;
    }

    private List<Product> filterResult(String regexp, List<Product> products) {
        String decodedRegexp = URLDecoder.decode(regexp, StandardCharsets.UTF_8);

        return products.stream()
            .filter(Predicate.not(product -> product.getName().matches(decodedRegexp)))
            .collect(Collectors.toList());
    }
}
