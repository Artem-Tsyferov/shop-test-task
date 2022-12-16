package ua.tsyferov.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("ShopApplication")
class ShopApplicationTest {

    @Test
    void shouldStartShopApplication_whenMainMethodIsInvoked() {
        ShopApplication.main(new String[] {});
    }
}