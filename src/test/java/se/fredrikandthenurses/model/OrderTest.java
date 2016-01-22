package se.fredrikandthenurses.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @Test
    public void twoOrdersWithSameParametersShouldBeEqual() {

        User user = new User("Fredrik", "alalala");
        Product product = new Product("1001", "Jackie D", 19.99);
        OrderRow row = new OrderRow(product, 10);
        List<OrderRow> orderRowList = new ArrayList<>();
        orderRowList.add(row);
        PersistableOrder persistableOrder = new PersistableOrder("101", user);
        User user2 = new User("Fredrik", "alalala");
        Product product2 = new Product("1001", "Jackie D", 19.99);
        OrderRow row2 = new OrderRow(product, 10);
        List<OrderRow> orderRowList2 = new ArrayList<>();
        orderRowList2.add(row2);
        PersistableOrder persistableOrder2 = new PersistableOrder("101", user2);
        assertEquals(persistableOrder, persistableOrder2);
    }

    @Test
    public  void equalOrdersShouldHaveSameHashCode(){

        User user = new User("Joanne", "123");
        Product product = new Product("1002", "corona", 10.00);
        OrderRow row = new OrderRow(product, 200);
        List<OrderRow> orderRows = new ArrayList<>();
        orderRows.add(row);

        PersistableOrder persistableOrder = new PersistableOrder("102", user);
        User user2 = new User("Joanne", "123");
        Product product2 = new Product("1002", "corona", 10.00);
        OrderRow row2 = new OrderRow(product2, 200);
        List<OrderRow> orderRows2 = new ArrayList<>();
        orderRows2.add(row2);
        PersistableOrder persistableOrder2 = new PersistableOrder("102", user2);
        assertEquals(persistableOrder, persistableOrder2);
        assertEquals(persistableOrder.hashCode(), persistableOrder2.hashCode());


    }



}