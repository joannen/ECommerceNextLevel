package se.fredrikandthenurses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.fredrikandthenurses.exception.RepositoryException;
import se.fredrikandthenurses.model.Order;
import se.fredrikandthenurses.model.OrderStatus;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;
import se.fredrikandthenurses.validation.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by joanne on 21/12/15.
 */
@Service
public class ECommerceService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    public ECommerceService(OrderRepository orderRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Product saveProduct(Product product) throws RepositoryException {
        Product p;
        if (Validator.isValid(product)) {
            p = productRepo.save(product);
        } else {
            throw new RepositoryException("Not valid product");
        }
        return p;
    }

    public Product findProductById(Long id) {
        return productRepo.findOne(id);
    }

    public Product findByProductNumber(String productNumber) throws RepositoryException {
        return productRepo.findByProductNumber(productNumber);
    }

    public Product findByProductName(String productName) throws RepositoryException {
        return productRepo.findByProductName(productName);
    }

    public List<Product> getAllProducts() throws RepositoryException {
        return (List<Product>) productRepo.findAll();
    }

    public User saveUser(User user) {
        User u;
        if (Validator.isValid(user)) {
            u = userRepo.save(user);

        } else {
            throw new RepositoryException("Not valid user");
        }
        return u;
    }

    public User findUserById(Long id) {
        return userRepo.findOne(id);
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public Order saveOrder(Order persistableOrder) {
        Order o;
        if (Validator.isValid(persistableOrder)) {
            o = orderRepo.save(persistableOrder);
        } else {
            throw new RepositoryException("Not valid order");
        }
        return o;
    }

    public Order findOrderById(Long id) {
        return orderRepo.findOne(id);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepo.findAll();
    }

    public Collection<Order> findOrdersByUser(User user) {
        Collection<Order> orderList;
        if (Validator.isValid(user)) {
            orderList = orderRepo.findByUser(user);
        } else {
            throw new RepositoryException("Not a valid user");
        }
        return orderList;
}

    public List<Order> findOrdersByStatus(OrderStatus status) {
        return new ArrayList<>(orderRepo.findOrdersByStatus(status));
    }

    public List<Order> findOrdersByMinimumPrice(double price) {
        return new ArrayList<>(orderRepo.findByMinimumPriceGreaterThan(price));
    }

    public Order findByOrderNumber(String orderNumber) {
        return orderRepo.findByOrderNumber(orderNumber);
    }
}
