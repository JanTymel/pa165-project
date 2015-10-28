package cz.muni.fi.pa165.tireservice.entity;

import cz.muni.fi.pa165.tireservice.enums.CarType;
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
@Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Customer customer;

    @OneToMany
    @NotNull
    private final List<Tire> tires = new ArrayList<Tire>();

    @OneToMany
    @NotNull
    private final List<Service> services = new ArrayList<Service>();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Enumerated
    @NotNull
    private OrderState state;

    @Enumerated
    @NotNull
    private CarType carType;

    public Order(Long id) {
        this.id = id;
    }

    public Order() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Tire> getTires() {
        return Collections.unmodifiableList(tires);
    }

    public void addTire(Tire tire) {
        tires.add(tire);
    }

    public List<Tire> getServices() {
        return Collections.unmodifiableList(services);
    }

    public void addService(Service service) {
        services.add(service);
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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getCustomer());
        hash = 29 * hash + Objects.hashCode(this.getTires());
        hash = 29 * hash + Objects.hashCode(this.getServices());
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
        if (!Objects.equals(this.customer, other.getCustomer())) {
            return false;
        }
        if (!Objects.equals(this.tires, other.getTires())) {
            return false;
        }
        if (!Objects.equals(this.services, other.getServices())) {
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
