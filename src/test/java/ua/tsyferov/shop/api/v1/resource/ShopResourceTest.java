package ua.tsyferov.shop.api.v1.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ua.tsyferov.shop.config.ResourceTestConfig;
import ua.tsyferov.shop.factory.ProductFactory;
import ua.tsyferov.shop.model.Product;
import ua.tsyferov.shop.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShopResource.class)
@Import(ResourceTestConfig.class)
@DisplayName("ShopResource")
class ShopResourceTest {

    private static final String REGEXP = "%5EE.%2A%24";

    private static final String BASE_URL = ShopResource.BASE_URL;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ProductService productServiceMock;

    @Test
    void getProductsByNameFilter_shouldReturnResponseEntityWithProducts() throws Exception {
        Product product = ProductFactory.createProduct();
        List<Product> expectedProducts = List.of(product);

        when(productServiceMock.getProductsByNameNotMatchRegexp(REGEXP)).thenReturn(expectedProducts);

        String expectedJson = mapper.writeValueAsString(expectedProducts);

        mockMvc.perform(get(BASE_URL + "/product?nameFilter=" + REGEXP))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));
    }
}