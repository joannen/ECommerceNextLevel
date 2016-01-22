package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.Product;

/**
 * Created by joanne on 21/12/15.
 */
public interface ProductRepository extends CrudRepository<Product> {

    Product findByProductNumber(String number);

    Product findByProductName(String name);

}
