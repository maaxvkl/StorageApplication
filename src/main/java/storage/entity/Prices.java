package storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    Integer lengthPrice;
    @Column
    Integer widthPrice;
    @Column
    Integer heightPrice;

    public Prices(){
    }

    public Prices(Integer lengthPrice, Integer widthPrice, Integer heightPrice) {
        this.lengthPrice = lengthPrice;
        this.widthPrice = widthPrice;
        this.heightPrice = heightPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getLengthPrice() {
        return lengthPrice;

    }
    public void setLengthPrice(Integer lengthPrice) {
        this.lengthPrice = lengthPrice;
    }
    public Integer getWidthPrice() {
        return widthPrice;
    }
    public void setWidthPrice(Integer widthPrice) {
        this.widthPrice = widthPrice;
    }
    public Integer getHeightPrice() {
        return heightPrice;
    }
    public void setHeightPrice(Integer heightPrice) {
        this.heightPrice = heightPrice;
    }


}

