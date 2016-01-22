import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.*;
import se.fredrikandthenurses.service.ECommerceService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by joanne on 18/01/16.
 */
public class Main {

    public static EntityManagerFactory factory =Persistence.createEntityManagerFactory("lokaldatabas");

    public static void main(String[] args) {

        UserRepository userRepository = new JpaUserRepository(factory);
        OrderRepository orderRepository = new JpaOrderRepository(factory);
        ProductRepository productRepository = new JpaProductRepository(factory);

        ECommerceService service = new ECommerceService(orderRepository, userRepository, productRepository);

        User user = new User("joanne", "abc");
        PersistableOrder order = new PersistableOrder("10", user);

        service.saveUser(user);
        service.saveOrder(order);

        PersistableOrder order1 = new PersistableOrder("11", user);

        service.saveOrder(order1);

        System.out.println(service.findOrdersByUser(user));



    }
}
