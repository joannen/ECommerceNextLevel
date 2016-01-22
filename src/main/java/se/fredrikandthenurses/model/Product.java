package se.fredrikandthenurses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class Product extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String productNumber;
    @Column(nullable = false, unique = true)
    private String productName;
    @Column(nullable = false)
    private Double productPrice;
    @Column
    private boolean available;

    protected Product() {}

    public Product(String productNumber, String productName, Double productPrice) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.available = true;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (available != product.available) return false;
        if (productNumber != null ? !productNumber.equals(product.productNumber) : product.productNumber != null)
            return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        return productPrice != null ? productPrice.equals(product.productPrice) : product.productPrice == null;
    }

    @Override
    public int hashCode() {
        int result = productNumber != null ? productNumber.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId="+ getId() + '\'' +
                "productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", available=" + available +
                '}';
    }
}
