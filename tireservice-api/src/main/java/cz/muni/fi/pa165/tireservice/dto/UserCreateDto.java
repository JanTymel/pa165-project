package cz.muni.fi.pa165.tireservice.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * CreateDto for entity User.
 *
 * @author Jan Tymel
 */
public class UserCreateDto {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @NotNull
    private Boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hash(this.name);
        hash = 13 * hash + Objects.hash(this.address);
        hash = 13 * hash + Objects.hash(this.phone);
        hash = 13 * hash + Objects.hash(this.isAdmin);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof UserCreateDto)) {
            return false;
        }

        final UserCreateDto other = (UserCreateDto) obj;

        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }

        if (!Objects.equals(this.address, other.getAddress())) {
            return false;
        }

        if (!Objects.equals(this.phone, other.getPhone())) {
            return false;
        }

        return (other.getIsAdmin().equals(this.isAdmin));
    }

    @Override
    public String toString() {
        return "UserCreateDto: name=" + name + ", address=" + address + ", phone=" + phone + ", isAdmin=" + isAdmin;
    }
}
