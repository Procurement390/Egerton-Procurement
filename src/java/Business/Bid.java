
package Business;

import java.io.Serializable;

public class Bid implements Serializable{
    
    private int Id;
    private String tenderNumber;
    private int amount;
    private String username;
    private String status;

    public Bid() {
    }


    public Bid(int Id, String username, int amount, String status) {
        this.Id = Id;
        this.username = username;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

  
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
}
