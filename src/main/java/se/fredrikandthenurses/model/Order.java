package se.fredrikandthenurses.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity(name = "PersistableOrder")
public class Order extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Collection<OrderRow> orderRowList;
    @Column(nullable = false, unique = true)
    private String orderNumber;
    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected Order() {
    }

    public Order(String orderNumber, User user) {
        this.user = user;
        this.orderRowList = new ArrayList<>();
        this.orderNumber = orderNumber;
        this.price = 0.0;
        orderStatus = OrderStatus.PLACED;
    }

    public User getUser() {
        return user;
    }

    public Collection<OrderRow> getOrderRowList()
    {
        return new HashSet<>(orderRowList);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatusShipped(){
        orderStatus = OrderStatus.SHIPPED;
    }

    public void setStatusPaid(){
        orderStatus = OrderStatus.PAID;
    }

    public void setStatusCanceled(){
        orderStatus = OrderStatus.CANCELLED;
    }

    public double getPrice(){
        double totalPrice =0;
        for (OrderRow row : orderRowList) {
            totalPrice += row.getPrice();
        }
        return totalPrice;
    }

    public Order addOrderRow(OrderRow row){
        this.orderRowList.add(row);
        this.price += row.getPrice();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return !(getOrderNumber() != null ? !getOrderNumber().equals(that.getOrderNumber()) : that.getOrderNumber() != null);
    }

    @Override
    public int hashCode() {
        return getOrderNumber() != null ? getOrderNumber().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + getId() + ", " +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

