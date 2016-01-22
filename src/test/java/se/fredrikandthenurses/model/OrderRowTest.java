package se.fredrikandthenurses.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joanne on 21/12/15.
 */
public class OrderRowTest {

    @Test
    public void testEquals() throws Exception {
        Product product = new Product("100", "dosxx", 12.00);
        OrderRow row = new OrderRow(product, 10);
        Product product1 = new Product("100", "dosxx", 12.00);
        OrderRow row1 = new OrderRow(product1, 10);
        assertEquals(row, row1);
    }

    @Test
    public void testHashCode() throws Exception {
        Product product = new Product("200", "staropramen", 10.00);
        OrderRow row = new OrderRow(product, 20);
        Product product1 = new Product("200", "staropramen", 10.00);
        OrderRow row1 = new OrderRow(product1, 20);
        assertEquals(row, row1);
        assertEquals(row.hashCode(), row1.hashCode());
    }
}