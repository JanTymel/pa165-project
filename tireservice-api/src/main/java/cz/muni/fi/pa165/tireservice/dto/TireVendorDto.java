package cz.muni.fi.pa165.tireservice.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * DTO class for entity TireVendor.
 *
 * @author Jan Tymel
 */
public class TireVendorDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof TireVendorDto)) {
            return false;
        }

        final TireVendorDto other = (TireVendorDto) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TireVendorDto: id=" + id + ", name=" + name;
    }

}
