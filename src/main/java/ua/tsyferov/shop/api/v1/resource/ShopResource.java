package ua.tsyferov.shop.api.v1.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.tsyferov.shop.config.ApiVersion;
import ua.tsyferov.shop.model.Product;
import ua.tsyferov.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = ShopResource.BASE_URL)
public class ShopResource {

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/shop";

    private final ProductService productService;

    @Autowired
    public ShopResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductsByNameFilter(
        @RequestParam("nameFilter") String nameFilter
    ) {
        List<Product> products = productService.getProductsByNameNotMatchRegexp(nameFilter);
        return ResponseEntity.ok().body(products);
    }
}
