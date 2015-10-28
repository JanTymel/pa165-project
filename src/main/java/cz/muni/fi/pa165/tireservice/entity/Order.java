package cz.muni.fi.pa165.tireservice.entity;

import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Tymel
 */
@Entity
@Table(name="ORDER")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=false)
	@NotNull
	private User user;

	@OneToMany
	@NotNull
	private final List<OrderItem> orderItems = new ArrayList<OrderItem>();

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Enumerated
	@NotNull
	private OrderState state;

	public Order(Long id) {
		this.id=id;
	}

	public Order() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(orderItems);
	}

	public void addOrderItem(OrderItem p) {
		orderItems.add(p);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getUser());
        hash = 29 * hash + Objects.hashCode(this.getOrderItems());
        hash = 29 * hash + Objects.hashCode(this.getCreated());
        hash = 29 * hash + Objects.hashCode(this.getState());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.user, other.getUser())) {
            return false;
        }
        if (!Objects.equals(this.orderItems, other.getOrderItems())) {
            return false;
        }
        if (!Objects.equals(this.created, other.getCreated())) {
            return false;
        }
        if (!Objects.equals(this.state, other.getState())) {
            return false;
        }
        return true;
    }


}
