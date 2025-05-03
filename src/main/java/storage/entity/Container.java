package storage.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "container")
public class Container{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 Integer ID;
	 @Column
	 @DateTimeFormat(pattern ="dd-MM-yyyy")
	 LocalDate storageDate;
	 @Column
	 int length;
	 @Column
	 int width;
	 @Column
	 int heigth;
	 @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	 @JoinColumn(name = "owned_By")
	 @JsonBackReference
	 User ownedBy;

	    public Container(Integer ID, LocalDate storageDate, int length, int width, int heigth, User ownedBy) {
	        this.ID = ID;
	        this.storageDate = storageDate;
	        this.length = length;
	        this.width = width;
	        this.heigth = heigth;
	        this.ownedBy = ownedBy;
	    }

	    public Container() {
	    }

	    public User getOwnedBy() {
	        return ownedBy;
	    }

	    public void setOwnedBy(User ownedBy) {
	        this.ownedBy = ownedBy;
	    }

	    public Integer getID() {
	        return ID;
	    }

	    public LocalDate getStorageDate() {
	        return storageDate;
	    }

	    public int getLength() {
	        return length;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public int getHeigth() {
	        return heigth;
	    }

	    public void setID(Integer ID) {
	        this.ID = ID;
	    }

	    public void setStorageDate(LocalDate storageDate) {
	        this.storageDate = storageDate;
	    }

	    public void setLength(int length) {
	        this.length = length;
	    }

	    public void setWidth(int width) {
	        this.width = width;
	    }

	    public void setHeigth(int height) {
	        this.heigth = height;
	    }
	}


