/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;

/**
 *
 * @author YU YUNG JUN
 */
public class Course {
    private String cCode;
    private String cName;
    private ListInterface<Programme> cProg;
    
  public Course(){
      this.cCode = null;
      this.cName = null;
      this.cProg = new ArrayList<>();
           }
    
    public Course(String cCode,String cName){
    this.cCode = cCode;
    this.cName = cName;
    this.cProg = new ArrayList<>();
    }

    public Course(String cCode) {
         this.cCode = cCode;
         this.cProg = new ArrayList<>();
    }
    public Course(String cCode, String cName, ListInterface<Programme> prog) {
        this.cCode = cCode;
        this.cName = cName;
        this.cProg = prog;
    }
 
    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
 
    public ListInterface<Programme> getAllProgramme() {
        return cProg;
    }
    
    public void addProgrammeToCourse(Programme prog) {
        cProg.add(prog);
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

        final Course other = (Course) obj;

        if (!(this.cCode.equals(other.cCode))) {
            return false;
        }

        return true;
    }
   @Override
    public String toString(){
        return String.format(" Course Code : %-10s Course Name : %-30s", cCode, cName);
    } 
}
