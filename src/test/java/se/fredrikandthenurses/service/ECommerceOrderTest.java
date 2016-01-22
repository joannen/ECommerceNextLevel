package se.fredrikandthenurses.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.OrderRow;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ECommerceOrderTest {

    @Rule
    public ExpectedException exception= ExpectedException.none();

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @InjectMocks
    private ECommerceService service;

    private User user;
    private OrderRow orderRow;
    private List<OrderRow> orderRowList;
    private Product product;
    private PersistableOrder persistableOrder;

    @Before
    public void setup() {
        user = new User("Fredrik", "alalalal");
        product = new Product("100", "Jackie D", 19.99);
        orderRow = new OrderRow(product, 100);
        orderRowList = new ArrayList<>();
        orderRowList.add(orderRow);
        persistableOrder = new PersistableOrder("1001", user);
    }

    @Test
    public void orderShouldBeAddedOrUpdated() {
        service.saveOrder(persistableOrder);
        verify(orderRepositoryMock).saveOrUpdate(persistableOrder);
    }

    @Test
    public void orderShouldBeRetrievableByOrderNumber() throws Exception {
        service.saveOrder(persistableOrder);
        verify(orderRepositoryMock).saveOrUpdate(persistableOrder);
        when(orderRepositoryMock.findByOrderNumber("1001")).thenReturn(persistableOrder);
        assertThat(service.findByOrderNumber("1001"), equalTo(persistableOrder));
        verify(orderRepositoryMock,times(1)).findByOrderNumber("1001");
    }

    @Test
    public void orderShouldBeRetrievableByUser() {
//        vi testar bara verify
        List<PersistableOrder> orderList = new ArrayList<>();
        service.saveOrder(persistableOrder);
        orderList.add(persistableOrder);
        verify(orderRepositoryMock).saveOrUpdate(persistableOrder);
        when(orderRepositoryMock.findByUser(user)).thenReturn(orderList);
        assertThat(service.findOrdersByUser(user), equalTo(orderList));
        verify(orderRepositoryMock, times(1)).findByUser(user);
    }

    @Test
    public void orderShouldBeRetrievableByStatus() {
        service.saveOrder(persistableOrder);
        verify(orderRepositoryMock).saveOrUpdate(persistableOrder);
        persistableOrder.setStatusShipped();
        List<PersistableOrder> shippedPersistableOrders = new ArrayList<>();
        shippedPersistableOrders.add(persistableOrder);
        when(orderRepositoryMock.findOrdersByStatus(persistableOrder.getStatus())).thenReturn(shippedPersistableOrders);
        assertThat(service.findOrdersByStatus(persistableOrder.getStatus()), contains(persistableOrder));
    }

    @Test
    public void ordersShouldBeRetrievableByMinimumPrice(){
        double price = 100;
        service.saveOrder(persistableOrder);
        List<PersistableOrder> persistableOrders = new ArrayList<>();
        persistableOrders.add(persistableOrder);
        verify(orderRepositoryMock).saveOrUpdate(persistableOrder);
        when(orderRepositoryMock.findByMinimumPrice(price)).thenReturn(persistableOrders);
        assertThat(service.findOrdersByMinimumPrice(price), contains(persistableOrder));
        verify(orderRepositoryMock, times(1)).findByMinimumPrice(price);
    }
}
