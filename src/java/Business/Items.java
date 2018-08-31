package Business;

import java.io.Serializable;


public class Items implements Serializable{

private int id;
private String item;
private String description;
private int quantity;

    public Items() {
    }

    public Items(Items items) {
        this.id = items.getId();
        this.item = items.getItem();
        this.description = items.getDescription();
        this.quantity = items.getQuantity();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    

}
