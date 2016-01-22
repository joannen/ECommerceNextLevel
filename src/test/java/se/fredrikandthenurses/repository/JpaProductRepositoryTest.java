package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.fredrikandthenurses.model.Product;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Created by TheYellowBelliedMarmot on 2015-12-22.
 */
public class JpaProductRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ProductRepository productRepo;
    private EntityManagerFactory emf;
    private Product product;

    @Before
    public void setup()
    {
        emf = Persistence.createEntityManagerFactory("lokaldatabas");
        productRepo = new JpaProductRepository(emf);
        product = new Product("123", "Brooklyn Lager", 15.90);
        productRepo.saveOrUpdate(product);
    }

    @Test
    public void productShouldBeFoundById(){
        assertThat(productRepo.find(product.getId()), equalTo(product));
    }

    @Test
    public void  productShouldBeUpdated(){
        assertTrue(productRepo.find(product.getId()).isAvailable());
        product.setAvailable(false);
        productRepo.saveOrUpdate(product);
        assertFalse(productRepo.find(product.getId()).isAvailable());
    }

    @Test
    public void productShouldBeRetrievableByNumber(){
        assertThat(productRepo.findByProductNumber("123"), equalTo(product));
    }

    @Test
    public void allProductsShouldBeRetrievable(){
        Product product1 = new Product("234", "sol", 11.99);
        productRepo.saveOrUpdate(product1);
        assertThat(productRepo.getAll(), contains(product, product1));
    }

    @Test
    public void deletedProductShouldBeDeleted(){
        assertThat(productRepo.getAll(), contains(product));
        productRepo.remove(product);
        assertFalse(productRepo.getAll().contains(product));
    }



}