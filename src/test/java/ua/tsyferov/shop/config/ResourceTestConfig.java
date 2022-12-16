package ua.tsyferov.shop.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.tsyferov.shop.service.DateTimeService;
import ua.tsyferov.shop.service.ProductService;

@TestConfiguration
@MockBean(ProductService.class)
@MockBean(DateTimeService.class)
public class ResourceTestConfig {
}
