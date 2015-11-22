package cz.muni.fi.pa165.tireservice.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * CreateDto for entity TireVendor.
 *
 * @author Jan Tymel
 */
public class TireVendorCreateDto {
    @NotNull
    private String name;

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

        if (!(obj instanceof TireVendorCreateDto)) {
            return false;
        }

        final TireVendorCreateDto other = (TireVendorCreateDto) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TireVendorCreateDto: name=" + name;
    }
}
