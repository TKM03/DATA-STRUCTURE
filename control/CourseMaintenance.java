/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import adt.*;
import boundary.*;
import entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;
import java.util.Scanner;
/**
 *
 * @author YU YUNG JUN
 */
public class CourseMaintenance {
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Programme> programmeList = new ArrayList<>(); 
    private CourseMaintenanceUI courseUI = new CourseMaintenanceUI();
    private ProgrammeMaintenanceUI programmeUI = new ProgrammeMaintenanceUI();
    private Scanner scanner = new Scanner(System.in);
    
    public void runCourseMaintenance(){
      courseList.add(new Course("AACS1123","Data Mining"));
      String choice;
        do{
            choice = courseUI.getDisplayMenuChoice();
            switch (choice) {
                case "1":
                    //Call add Course Function
                    addCourse();   
                    break;
                case "2":
                    //Call remove course Function
                    deleteCourse();
                    break;
                case "3":
                    //Call find course Function
                    findCourse();
                    break;
                case "4":
                    //Call amend course Function
                    amendCourse();
                    break;
                case "5":
                    //Call display course Function 
                    displayCourse();
                    break;
                case "6":
                    //Call add programme to Course Function 
                    addProgrammeToCourse();
                    break;
                case "7":
                    //Call remove programme from Course Function 
                    deleteProgramme();
                    break;
                case "8":
                    //Call remove programme from Course Function 
                    courseReport();
                    break;
                case "0":
                    //display exist message to user and stop the system
                    MessageUI.displayExitMessage();
                    break;
                default:
                    MessageUI.displayRetypeMessage();
        }
    }while(!choice.equals("0"));
}
    public void addCourse(){
    
        Course cr = courseUI.inputCourseDetails();
        
//        if the course code are same it will display error message
        if(courseList.getEntry(cr)!= null){
            
            MessageUI.displayCourseExistMessage();
                          
        }else{
//            add the course data into course list
            courseList.add(cr);
        
            System.out.println("\n");
            MessageUI.displayCourseAddedMessage();  
        }
    }
    
    public void deleteCourse(){
       
        courseUI.deleteUI();
        
//        print out the course in the courselist
        for (int i=1; i < courseList.getNumberOfEntries()+1;i++){
               System.out.println("\t "+ i + " > " + courseList.getEntry(i));
           }
        int deleteNo;
        deleteNo = courseUI.getDeleteNo();
//        if the number insert are correct , the course will be deleted else display error mesej
        if (deleteNo >= 1 && deleteNo <= courseList.getNumberOfEntries()) {
            System.out.println("\t"+courseList.getEntry(deleteNo));
            courseList.remove(deleteNo);
            MessageUI.displayCourseDeletedMessage();
        } else {
            MessageUI.displayInvalidNumMessage();
            }
    }
    
    public void findCourse(){
        String cCode;
        cCode = courseUI.fCourse();
        
        Course cr = courseList.getEntry(new Course(cCode));
        
        if(cr != null){           
            System.out.println("\t Course : \n\t" + cr);    
        }else{
            MessageUI.displayNoCourseFoundMessage();
        }
    }
    
    public void amendCourse(){
        courseUI.amendUI();
        
        if(courseList.getNumberOfEntries() != 0){
            for (int i=1; i < courseList.getNumberOfEntries()+1;i++){
               System.out.println("\t "+ i + " > " + courseList.getEntry(i));
            }
                int amendNo;
                amendNo = courseUI.getAmendNo();
                  
                //Course courseAmend = courseUI.aCourse(cCode);
                Course cr = courseUI.amendC();
               
                    if (amendNo >= 1 && amendNo <= courseList.getNumberOfEntries()) {
                            //courseList.replace(amendNo, courseAmend);
                            courseList.replace(amendNo, cr);
                            MessageUI.displayCourseModifiedMessage();
                    }else{
                            MessageUI.displayInvalidNumMessage();
                    }
            

        } else{
                MessageUI.displayNoCourseFoundMessage();
              }
    }
            
    public void displayCourse(){
        courseUI.dCourse();
//        if there is record in the course list it will display out else display  course found mesej
        if(courseList.getNumberOfEntries()!=0){
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {  
           System.out.println("\t Course : \n\t" +courseList.getEntry(i + 1));
        }
        }else{
            MessageUI.displayNoCourseFoundMessage();
        }
    }
     public void addProgrammeToCourse(){
       
        String cCode = programmeUI.addProgToCourse();
        
        Course cr = courseList.getEntry(new Course(cCode));
        if(cr != null){
            
            String pCode = programmeUI.aProgramme();
            Programme p = programmeList.getEntry(new Programme(pCode));
            
            if (p == null) {
            //if programme not found
            //ask user to enter new programme  details
            //assign new programme to programme list
            //add to course
            Programme programmeAdd = programmeUI.addProgramme(pCode);
            //Course cr;
                do {
                    cr = courseList.getEntry(new Course(cCode));
                } while (cr == null);
               
            //assign programme to course
            //--- this is the reason why ADT need to be inside ENTITY
                    cr.addProgrammeToCourse(programmeAdd);
                    MessageUI.displayProgAddedMessage();
            } else {
         
                do{
                    cr = courseList.getEntry(new Course(cCode));
                } while (cr == null);

            //assign programme to course
            //--- this is the reason why ADT need to be inside ENTITY
                    cr.addProgrammeToCourse(p);
                    MessageUI.displayProgAddedMessage();
                }
            
        }else{
            MessageUI.displayNoCourseFoundMessage();
        }
        
    } 
     
    public void deleteProgramme(){
        
       String cCode = programmeUI.deleteProgFromCourse();
        
        Course cr = courseList.getEntry(new Course(cCode));
        if(cr != null){
            
            if (cr.getAllProgramme().getNumberOfEntries() != 0) {
                System.out.println("\n");
                System.out.println("\t Course Info : \n\t" + cr);
                for (int i = 1; i < cr.getAllProgramme().getNumberOfEntries() + 1; i++) {
                    System.out.println("\t "+ i + " > " + cr.getAllProgramme().getEntry(i));
                }
                int deleteNo;
                deleteNo = courseUI.getDeleteNo();
                
                if (deleteNo >= 1 && deleteNo <= cr.getAllProgramme().getNumberOfEntries()) {
//                    System.out.println("\t"+cr.getAllProgramme().getEntry(deleteNo));
                    cr.getAllProgramme().remove(deleteNo);
                    MessageUI.displayProgDeletedMessage();
                } else {
                    MessageUI.displayInvalidNumMessage();
                }
            }else {
            MessageUI.displayRetypeMessage();
        }
        }else{
            MessageUI.displayNoCourseFoundMessage();
        }
            
    }
    public void courseReport(){
        courseUI.courseRep();
        LocalDate currentDate = LocalDate.now();   
   
        // Define date and time format patterns
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format the current date and time as strings
        String formattedDate = currentDate.format(dateFormatter);
        
        // Print the current date and  separately
        System.out.println("Date: " + formattedDate + "\n");
      int numberOfEntries = courseList.getNumberOfEntries();
        if(courseList.getNumberOfEntries()!=0){
            
            for (int i = 0; i < courseList.getNumberOfEntries(); i++) {  
           
                System.out.println("\t Course : \n\t" +courseList.getEntry(i + 1));
                System.out.println("\t" + courseList.getEntry(i + 1).getAllProgramme());
                
            }
        }else{
            MessageUI.displayNoCourseFoundMessage();            
        }
        System.out.println("\n\tCourse added today : " + numberOfEntries);
    }
    }

