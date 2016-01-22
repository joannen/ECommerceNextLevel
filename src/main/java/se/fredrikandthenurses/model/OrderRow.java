package se.fredrikandthenurses.model;

import javax.persistence.*;

@Entity
public class OrderRow extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name= "productId", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer amount;

    public OrderRow(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    protected OrderRow() {}

    public double getPrice()
    {
        return product.getProductPrice()*amount;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRow orderRow = (OrderRow) o;

        if (product != null ? !product.equals(orderRow.product) : orderRow.product != null) return false;
        return !(amount != null ? !amount.equals(orderRow.amount) : orderRow.amount != null);
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderRow{" +
                "product=" + product +
                ", amount=" + amount +
                 +
                '}';
    }

}
