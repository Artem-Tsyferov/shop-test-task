package ua.tsyferov.shop.service;

import ua.tsyferov.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByNameNotMatchRegexp(String regexp);
}
