package ua.tsyferov.shop.factory;

import ua.tsyferov.shop.model.Product;

public class ProductFactory {

    private static final String NAME = "Test";

    private static final String DESCRIPTION = "Test description";

    private ProductFactory () {}

    public static Product createProduct () {
        return createProduct(NAME, DESCRIPTION);
    }

    public static Product createProduct (String name, String description) {
        return new Product(name, description);
    }
}
