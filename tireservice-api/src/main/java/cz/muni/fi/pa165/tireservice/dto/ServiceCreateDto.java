
package cz.muni.fi.pa165.tireservice.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * CreateDto for entity Service.
 *
 * @author Jan Tymel
 */
public class ServiceCreateDto {

    @NotNull
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash = 37 * hash + Objects.hashCode(this.getName());
        hash = 37 * hash + Objects.hashCode(this.getPrice());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof ServiceDto)) {
            return false;
        }

        final ServiceDto other = (ServiceDto) obj;

        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }

        if (!Objects.equals(this.price, other.getPrice())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "ServiceCreateDto: name=" + name + ", price=" + price;
    }
}
