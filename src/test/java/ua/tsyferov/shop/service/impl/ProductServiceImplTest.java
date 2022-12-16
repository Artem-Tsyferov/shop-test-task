package ua.tsyferov.shop.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tsyferov.shop.exception.ResourceNotFoundException;
import ua.tsyferov.shop.factory.ProductFactory;
import ua.tsyferov.shop.model.Product;
import ua.tsyferov.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductServiceTest")
class ProductServiceImplTest {

    private static final String REGEXP = "%5EE.%2A%24";

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProductsByNameNotMatchRegexp_shouldReturnFilteredListOfProducts() {
        Product product = ProductFactory.createProduct();
        List<Product> expectedProducts = List.of(product);

        when(productRepositoryMock.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getProductsByNameNotMatchRegexp(REGEXP);

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void getProductsByNameNotMatchRegexp_shouldThrowResourceNotFoundException_whenResultIsEmpty() {
        when(productRepositoryMock.findAll()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductsByNameNotMatchRegexp(REGEXP));
    }
}