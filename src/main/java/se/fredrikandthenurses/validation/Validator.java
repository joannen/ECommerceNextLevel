package se.fredrikandthenurses.validation;

import se.fredrikandthenurses.model.*;

import java.util.Collection;

/**
 * Created by TheYellowBelliedMarmot on 2016-01-13.
 */
public class Validator{

    private Validator(){}

    public static boolean isValid(Product entity) {
        return(!productNameIsEmpty(entity.getProductName()) && priceIsOk(entity.getProductPrice()) && !productNumberIsEmpty(entity.getProductNumber()));
    }

    public static boolean isValid(User entity) {
       return !passwordIsEmpty(entity.getPassword());
    }

    public static boolean isValid(Order entity) {
        return(orderNumberIsEmpty(entity.getOrderNumber())|| listOfOrderRowsIsEmpty(entity.getOrderRowList()) ||
                priceMoreThan50K(entity.getPrice()));
    }

    private static boolean productNameIsEmpty(String productName){
        return productName.trim().length() < 1;
    }

    private static boolean priceIsOk(Double price){
        return price > 0;
    }

    private static boolean productNumberIsEmpty(String productNumber){
        return productNumber.trim().length() < 1;
    }

    private static boolean passwordIsEmpty(String password){
        return password.trim().length()<1;
    }

    private static boolean orderNumberIsEmpty(String orderNumber){
        return orderNumber.trim().length() <1;
    }

    private static boolean listOfOrderRowsIsEmpty(Collection<OrderRow> rows){
        return rows.size()<1;
    }

    private static boolean priceMoreThan50K(Double price){
        return price > 50000.00;
    }
}
