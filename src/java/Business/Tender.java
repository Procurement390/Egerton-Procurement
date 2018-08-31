package Business;

import java.io.Serializable;

public class Tender implements Serializable{
    
    private String tenderNumber;
    private String tenderDescription;
    private String closingDate;
    private String closingTime;
    private String status;
    private int count;

    public Tender() {
    }

    public Tender(String tenderNumber, String tenderDescription, String closingDate, String closingTime, String status) {
        this.tenderNumber = tenderNumber;
        this.tenderDescription = tenderDescription;
        this.closingDate = closingDate;
        this.closingTime = closingTime;
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    
    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getTenderDescription() {
        return tenderDescription;
    }

    public void setTenderDescription(String tenderDescription) {
        this.tenderDescription = tenderDescription;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

    

