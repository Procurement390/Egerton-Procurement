package Business;

import java.io.Serializable;


public class Requisition implements Serializable{

private String id;
private String department;
private String faculty;
private String username;

    public Requisition() {
    }

    public Requisition(Requisition req) {
        this.id = req.getId();
        this.department = req.getDepartment();
        this.faculty = req.getFaculty();
        this.username = req.getUsername();
    }    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
