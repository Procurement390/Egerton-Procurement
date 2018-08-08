package Business;


public class Supplier extends User{

private String companyName;
private String taxCompliance;
private String kraCertificate;
private String businessPermit;


    public Supplier() {
    }

    public Supplier(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxCompliance() {
        return taxCompliance;
    }

    public void setTaxCompliance(String taxCompliance) {
        this.taxCompliance = taxCompliance;
    }

    public String getKraCertificate() {
        return kraCertificate;
    }

    public void setKraCertificate(String kraCertificate) {
        this.kraCertificate = kraCertificate;
    }

    public String getBusinessPermit() {
        return businessPermit;
    }

    public void setBusinessPermit(String businessPermit) {
        this.businessPermit = businessPermit;
    }

    

}
