package se.fredrikandthenurses.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joanne on 21/12/15.
 */
public class ProductTest {

    @Test
    public void testEquals() throws Exception {
        Product product = new Product("200", "corona", 10.00);
        Product product1 = new Product("200", "corona", 10.00);
        assertEquals(product, product1);
    }

    @Test
    public void testHashCode() throws Exception {
        Product product = new Product("200", "sol", 11.00);
        Product product1 = new Product("200", "sol", 11.00);
        assertEquals(product, product1);
        assertEquals(product.hashCode(), product1.hashCode());
    }
}