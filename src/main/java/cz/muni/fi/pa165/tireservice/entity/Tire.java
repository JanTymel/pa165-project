package cz.muni.fi.pa165.tireservice.entity;

import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Tymel
 */
@Entity
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Enumerated
    @NotNull
    private CarType carType;

    @NotNull
    private int diameter;

    @NotNull
    private int width;

    @NotNull
    @OneToOne
    @JoinTable(name="CURRENT_VENDOR")
    private TireVendor tireVendor;

    @NotNull
    private String speedIndex;

    @NotNull
    private BigDecimal price;

    public Tire() {
    }

    public Tire(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public TireVendor getTireVendor() {
        return tireVendor;
    }

    public void setTireVendor(TireVendor tireVendor) {
        this.tireVendor = tireVendor;
    }

    public String getSpeedIndex() {
        return speedIndex;
    }

    public void setSpeedIndex(String speedIndex) {
        this.speedIndex = speedIndex;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.carType);
        hash = 13 * hash + this.diameter;
        hash = 13 * hash + this.width;
        hash = 13 * hash + Objects.hashCode(this.tireVendor);
        hash = 13 * hash + Objects.hashCode(this.speedIndex);
        hash = 13 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Tire)) {
            return false;
        }
        final Tire other = (Tire) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (this.carType != other.getCarType()) {
            return false;
        }
        if (this.diameter != other.getDiameter()) {
            return false;
        }
        if (this.width != other.getWidth()) {
            return false;
        }
        if (!Objects.equals(this.tireVendor, other.getTireVendor())) {
            return false;
        }
        if (!Objects.equals(this.speedIndex, other.getSpeedIndex())) {
            return false;
        }
        if (!Objects.equals(this.price, other.getPrice())) {
            return false;
        }
        return true;
    }
}
