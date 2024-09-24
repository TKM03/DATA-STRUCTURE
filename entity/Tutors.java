package entity;

import java.util.Objects;


/**
 *
 * @author Tongkitming
 */

public class Tutors {

    private String tID;
    private String tName;
    private String tSubject;
    private String tProgramme;
    private int Salary;


    public Tutors(String ID, String name, String subject, String tProgramme, int Salary) {
        this.tID = ID;
        this.tName = name;
        this.tSubject = subject;
        this.tProgramme = tProgramme;
        this.Salary = Salary;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public String gettProgramme() {
        return tProgramme;
    }

    public void settProgramme(String tProgramme) {
        this.tProgramme = tProgramme;
    }

    public String getName() {
        return tName;
    }

    public String getSubject() {
        return tSubject;
    }
    
    public void settName(String tName) {
        this.tName = tName;
    }

    public void settSubject(String tSubject) {
        this.tSubject = tSubject;
    }

    public void updateDetails(String newDetails) {
        String[] details = newDetails.split(",");
        if (details.length == 2) {
            this.tName = details[0].trim();
            this.tSubject = details[1].trim();
            System.out.println("Tutor details updated.");
        } else {
            System.out.println("Invalid details format.");
        }
    }
    
    @Override
  public int hashCode() {
    int hash = 3;
    return hash;
  }
    
    @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Tutors other = (Tutors) obj;
    return Objects.equals(this.tID, other.tID);
  }
  
  public boolean matchesCriteria(String criteria) {
        return tName.contains(criteria);
    }

    @Override
    public String toString() {
        return "\nTutor ID : "+ tID +"\nTutor Name : " + tName + "\nTutor Subject : "+ tSubject + "\nProgramme Teaching : "+ tProgramme +"\nSalary :"+Salary;
    }
}
