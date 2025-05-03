package storage.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
	private Integer userProfileId;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id")
    @MapsId
    private User user;	
	@Column
	private Integer containersStored;
	@Column
	private Integer totalCost;
	
	public UserProfile() {
		
	}
	
	public UserProfile(Integer userProfileId, User user, Integer containersStored, Integer totalCost) {
		super();
		this.userProfileId = userProfileId;
		this.user = user;
		this.containersStored = containersStored;
		this.totalCost = totalCost;
	}

	public Integer getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getContainersStored() {
		return containersStored;
	}

	public void setContainersStored(Integer containersStored) {
		this.containersStored = containersStored;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
}
