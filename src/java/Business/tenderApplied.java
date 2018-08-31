package Business;

import java.io.Serializable;

public class tenderApplied implements Serializable {
  
    private String tenderNumber;
    private String description;
    private String closingDate;
    private String closingTime;
    private String amount;
    private String status;

    public tenderApplied() {
    }

    public tenderApplied(String tenderNumber, String description, String closingDate, String closingTime, String amount, String status) {
        this.tenderNumber = tenderNumber;
        this.description = description;
        this.closingDate = closingDate;
        this.closingTime = closingTime;
        this.amount = amount;
        this.status = status;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
