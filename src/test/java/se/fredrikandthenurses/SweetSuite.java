package se.fredrikandthenurses;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import se.fredrikandthenurses.model.OrderRowTest;
import se.fredrikandthenurses.model.OrderTest;
import se.fredrikandthenurses.model.ProductTest;
import se.fredrikandthenurses.model.UserTest;
import se.fredrikandthenurses.repository.JpaOrderRepositoryTest;
import se.fredrikandthenurses.repository.JpaProductRepositoryTest;
import se.fredrikandthenurses.repository.JpaUserRepositoryTest;
import se.fredrikandthenurses.service.ECommerceOrderTest;
import se.fredrikandthenurses.service.ECommerceProductTest;
import se.fredrikandthenurses.service.ECommerceUserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OrderRowTest.class,
        OrderTest.class,
        ProductTest.class,
        UserTest.class,
        JpaOrderRepositoryTest.class,
        JpaProductRepositoryTest.class,
        JpaUserRepositoryTest.class,
        ECommerceOrderTest.class,
        ECommerceProductTest.class,
        ECommerceUserTest.class
})
public class SweetSuite {
}
