package cz.muni.fi.pa165.tireservice.dto;

import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Order entity
 * 
 * @author Samuel Baniar
 */
public class OrderDto {
    
    private Long id;
    
    private UserDto customer;
    
    private List<ServiceDto> services = new ArrayList<>();
    
    private List<TireDto> tires = new ArrayList<>();
    
    private Date created;
    
    private OrderState state;
    
    private CarType carType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public List<ServiceDto> getServices() {
        return services;
    }

    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }

    public List<TireDto> getTires() {
        return tires;
    }

    public void setTires(List<TireDto> tires) {
        this.tires = tires;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDto other = (OrderDto) obj;
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

    @Override
    public String toString() {
        return "Order: id=" + id + ", customer=" + customer + ", tires=" + tires + ", services=" + services + ", created=" + created + ", state=" + state + ", carType=" + carType;
    }
}
