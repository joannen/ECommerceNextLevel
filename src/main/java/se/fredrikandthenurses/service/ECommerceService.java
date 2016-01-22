package se.fredrikandthenurses.service;

import se.fredrikandthenurses.exception.RepositoryException;
import se.fredrikandthenurses.model.OrderStatus;
import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;
import se.fredrikandthenurses.validation.Validator;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by joanne on 21/12/15.
 */
public final class ECommerceService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public ECommerceService(OrderRepository orderRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Product saveProduct(Product product) throws RepositoryException {
        Product p;
        if (Validator.isValid(product)) {
            p = productRepo.saveOrUpdate(product);
        } else {
            throw new RepositoryException("Not valid product");
        }
        return p;
    }

    public Product findProductById(Long id) {
        return productRepo.find(id);
    }

    public Product findByProductNumber(String productNumber) throws RepositoryException {
        return productRepo.findByProductNumber(productNumber);
    }

    public Product findByProductName(String productName) throws RepositoryException {
        return productRepo.findByProductName(productName);
    }

    public List<Product> getAllProducts() throws RepositoryException {
        return productRepo.getAll();
    }

    public User saveUser(User user) {
        User u;
        if (Validator.isValid(user)) {
            u = userRepo.saveOrUpdate(user);

        } else {
            throw new RepositoryException("Not valid user");
        }
        return u;
    }

    public User findUserById(Long id) {
        return userRepo.find(id);
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepo.getAll();
    }

    public PersistableOrder saveOrder(PersistableOrder persistableOrder) {
        PersistableOrder o;
        if (Validator.isValid(persistableOrder)) {
            o = orderRepo.saveOrUpdate(persistableOrder);
        } else {
            throw new RepositoryException("Not valid order");
        }
        return o;
    }

    public PersistableOrder findOrderById(Long id) {
        return orderRepo.find(id);
    }

    public List<PersistableOrder> getAllOrders() {
        return orderRepo.getAll();
    }

    public Collection<PersistableOrder> findOrdersByUser(User user) {
        Collection<PersistableOrder> orderList;
        if (Validator.isValid(user)) {
            orderList = orderRepo.findByUser(user);
        } else {
            throw new RepositoryException("Not a valid user");
        }
        return orderList;
}

    public List<PersistableOrder> findOrdersByStatus(OrderStatus status) {
        return new ArrayList<>(orderRepo.findOrdersByStatus(status));
    }

    public List<PersistableOrder> findOrdersByMinimumPrice(double price) {
        return new ArrayList<>(orderRepo.findByMinimumPrice(price));
    }

    public PersistableOrder findByOrderNumber(String orderNumber) {
        return orderRepo.findByOrderNumber(orderNumber);
    }
}
