package se.fredrikandthenurses.repository;

import org.springframework.data.repository.CrudRepository;
import se.fredrikandthenurses.model.Product;

/**
 * Created by joanne on 21/12/15.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByProductNumber(String prductNumber);

    Product findByProductName(String productName);


}
