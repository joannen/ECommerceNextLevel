package se.fredrikandthenurses.repository;

import org.hibernate.TransientPropertyValueException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.fredrikandthenurses.exception.RepositoryException;
import se.fredrikandthenurses.model.*;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

/**
 * Created by joanne on 22/12/15.
 */
public class JpaOrderRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private Product product;
    private OrderRow orderRow;
    private User user;
    private PersistableOrder persistableOrder;
    private EntityManagerFactory factory;

    @Before
    public void setup(){
        factory = Persistence.createEntityManagerFactory("lokaldatabas");
        orderRepository = new JpaOrderRepository(factory);
        userRepository = new JpaUserRepository(factory);
        productRepository = new JpaProductRepository(factory);
        product = new Product("789", "corona", 10.00);
        orderRow =new OrderRow(product, 10);
        user = new User("joanne", "123");
        userRepository.saveOrUpdate(user);
        persistableOrder = new PersistableOrder("123", user);
        productRepository.saveOrUpdate(product);
        persistableOrder.addOrderRow(orderRow);
        orderRepository.saveOrUpdate(persistableOrder);
    }

    @Test
    public void testFindOrderById(){
        assertThat(orderRepository.find(persistableOrder.getId()), equalTo(persistableOrder));
    }

    @Test
    public void testFindByOrderNumber() throws Exception {
        assertThat(orderRepository.findByOrderNumber("123"), equalTo(persistableOrder));
    }

    @Test
    public void testFindByUser() throws Exception {
        PersistableOrder persistableOrderTwo = new PersistableOrder("124", user);
        orderRepository.saveOrUpdate(persistableOrderTwo);
        assertThat(orderRepository.findByUser(user), contains(persistableOrder, persistableOrderTwo));
    }

    @Test
    public void testFindOrdersByStatus() throws Exception {
        assertThat(orderRepository.findOrdersByStatus(persistableOrder.getStatus()), contains(persistableOrder));
}

    @Test
    public void testFindByMinimumPrice() throws Exception {
        assertThat(orderRepository.findByMinimumPrice(99.00), contains(persistableOrder));
    }

    @Test
    public void testGetAll(){
        assertThat(orderRepository.getAll(), contains(persistableOrder));
    }

    @Test
    public void testUpdatedOrderShouldBeUpdated(){
        OrderRow anotherRow = new OrderRow(product, 50);
        persistableOrder.addOrderRow(anotherRow);
        orderRepository.saveOrUpdate(persistableOrder);
        assertThat(orderRepository.find(persistableOrder.getId()).getOrderRowList(), contains(orderRow, anotherRow));
    }

    @Test
    public void testStatusShouldBeChanged(){
        assertTrue(orderRepository.find(persistableOrder.getId()).getStatus().equals(OrderStatus.PLACED));
        persistableOrder.setStatusShipped();
        orderRepository.saveOrUpdate(persistableOrder);
        assertTrue(orderRepository.find(persistableOrder.getId()).getStatus().equals(OrderStatus.SHIPPED));
    }

    @Test
    public void cantAddNonExistingProduct() throws Exception {
        Product p = new Product("123", "Uno", 19.99);
        OrderRow oR = new OrderRow(p, 15);
        PersistableOrder order = new PersistableOrder("12345", user);
        order.addOrderRow(oR);
        expectedException.expect(IllegalStateException.class);
        orderRepository.saveOrUpdate(order);

    }

    @Test
    public void userShouldNotBeNull(){
        PersistableOrder order = new PersistableOrder("12", null);
        expectedException.expect(RepositoryException.class);
        orderRepository.saveOrUpdate(order);
    }
}