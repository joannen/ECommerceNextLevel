import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.fredrikandthenurses.model.Order;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.*;
import se.fredrikandthenurses.service.ECommerceService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by joanne on 18/01/16.
 */
public class Main {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()){

            context.scan("se.fredrikandthenurses");
            context.refresh();

            ECommerceService eCommerceService = context.getBean(ECommerceService.class);

            eCommerceService.saveProduct(new Product("1", "beer", 20.00));
            System.out.println(eCommerceService.findByProductNumber("1"));

        }
    }
}
