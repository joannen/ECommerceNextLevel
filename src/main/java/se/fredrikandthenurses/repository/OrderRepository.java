package se.fredrikandthenurses.repository;

import org.springframework.data.repository.CrudRepository;
import se.fredrikandthenurses.model.Order;
import se.fredrikandthenurses.model.OrderStatus;
import se.fredrikandthenurses.model.User;

import java.util.List;

/**
 * Created by joanne on 21/12/15.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByOrderStatus (OrderStatus orderStatus);

    Order findByOrderNumber(String orderNumber);

    List<Order> findByPriceGreaterThan(Double price);
}
