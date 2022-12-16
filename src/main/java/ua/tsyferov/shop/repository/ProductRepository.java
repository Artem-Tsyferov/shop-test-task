package ua.tsyferov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.tsyferov.shop.model.Product;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger>, JpaSpecificationExecutor<Product> {

    /*
    Example of using native query to perform regexp filtering by database

    @Query(value = "SELECT * FROM products WHERE name !~ :filter", nativeQuery = true)
    List<Product> getAllByNameNotLikeFilter(@Param("filter") String filter);
     */
}
