package storage.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer userID;
    @Column
    @Valid
    @NotEmpty
    String email;
    @Column
    @NotEmpty
    String password;
    @Column
    boolean isActive;
    @Column
    Date registrationDate;
    @OneToMany(targetEntity = Container.class, cascade = CascadeType.ALL, mappedBy = "ownedBy")
    @JsonManagedReference
    private List<Container> containerList;
    private String role; //ROLE_USER{ read, edit }, ROLE_ADMIN {delete}
    private String[] authorities;
   


    public User(Integer userID, String email, String password, boolean isActive, Date created, List<Container> containerList,
    		    String role, String[] authorities) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.registrationDate = created;
        this.containerList = containerList;
        this.role = role;
        this.authorities = authorities;
     
    }

    public User() {

    }

    public Integer getUserID() {
        return userID;
    }

    public List<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<Container> containerList) {
        this.containerList = containerList;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	
	
	
	

	
}

