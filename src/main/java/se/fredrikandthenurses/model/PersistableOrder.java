package se.fredrikandthenurses.model;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity//skicka in name
@NamedQueries(value = {
        @NamedQuery(name = "PersistableOrder.FindByUser", query = "SELECT p FROM PersistableOrder p WHERE p.user.id = ?1"),
        @NamedQuery(name = "PersistableOrder.FindByOrderNumber", query = "SELECT p FROM PersistableOrder p Join Fetch p.orderRowList WHERE p.orderNumber = ?1"),
        @NamedQuery(name = "PersistableOrder.FindByStatus", query = "SELECT p FROM PersistableOrder p Join Fetch p.orderRowList WHERE p.orderStatus = ?1"),
        @NamedQuery(name = "PersistableOrder.FindByMinimumPrice", query = "SELECT p FROM PersistableOrder p where p.price >= ?1 "),
        @NamedQuery(name = "PersistableOrder.getAll", query = "SELECT p FROM PersistableOrder p")
})
public class PersistableOrder extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    //element collection
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "orderId")
    private Collection<OrderRow> orderRowList;
    @Column(nullable = false, unique = true)
    private String orderNumber;
    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    protected PersistableOrder() {
    }

    public PersistableOrder(String orderNumber, User user) {
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

    public PersistableOrder addOrderRow(OrderRow row){
        this.orderRowList.add(row);
        this.price += row.getPrice();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistableOrder that = (PersistableOrder) o;
        return !(getOrderNumber() != null ? !getOrderNumber().equals(that.getOrderNumber()) : that.getOrderNumber() != null);
    }

    @Override
    public int hashCode() {
        return getOrderNumber() != null ? getOrderNumber().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PersistableOrder{" + "id=" + getId() + ", " +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

