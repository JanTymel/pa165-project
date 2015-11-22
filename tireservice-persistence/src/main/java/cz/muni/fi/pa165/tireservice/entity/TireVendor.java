package cz.muni.fi.pa165.tireservice.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan Tymel
 */
@Entity
public class TireVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    public TireVendor(Long id) {
        this.id = id;
    }

    public TireVendor() {
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
        if (!(obj instanceof TireVendor)) {
            return false;
        }
        final TireVendor other = (TireVendor) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TireVendor: id=" + id + ", name=" + name;
    }


}
