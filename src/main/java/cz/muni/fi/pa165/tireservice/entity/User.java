package cz.muni.fi.pa165.tireservice.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel Baniar
 */
@Entity
@Table(name = "USER")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    private String address;
    
    @NotNull
    private String phone;
    
    @NotNull
    private Boolean isAdmin;
     
    public User(){        
    }
    
    public User(Long id){
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
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

    public void setPhone(String Phone) {
        this.phone = Phone;
    }    
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 13 * hash + Objects.hash(this.name);
        hash = 13 * hash + Objects.hash(this.address);
        hash = 13 * hash + Objects.hash(this.phone);
        hash = 13 * hash + Objects.hash(this.isAdmin);
        
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof User)){
            return false;
        }
        
        final User other = (User)obj;
        
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        
        if (!Objects.equals(this.address, other.getAddress())){
            return false;
        }
        
        if (!Objects.equals(this.phone, other.getPhone())){
            return false;
        }
        
        return (other.getIsAdmin() && this.isAdmin);
    }
}
