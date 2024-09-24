/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;
import entity.*;
import adt.ArrayList;
import adt.ListInterface;
import java.util.Scanner;
/**
 *
 * @author YU YUNG JUN/Fong Wei Sheng
 */
public class ProgrammeMaintenanceUI {
    Scanner scanner = new Scanner(System.in);
    private ListInterface<Programme> programmeList = new ArrayList<>();
    
    public String addProgToCourse(){
        System.out.println("\n\n");
        System.out.println("\t ------------------------");
        System.out.println("\t Add Programme To Course");
        System.out.println("\t ------------------------");
        
        System.out.print("\t Enter Course Code to Add Programme to Course: ");
        String cCode = scanner.nextLine();
        
        return cCode;
    } 
    
    public String aProgramme(){
        System.out.print("\t Enter Programme Code : ");
        String pCode = scanner.nextLine();
        
        return pCode;
    }
    
    public Programme addProgramme(String pCode){

        System.out.print("\t Enter Programme Name: ");
        String pName = scanner.nextLine();
        
        System.out.print("\t Enter Credit Hours > ");
        int creditHours = scanner.nextInt();
        scanner.nextLine();
        
       //Declare object
        Programme p = new Programme(pCode, pName, creditHours);
        //add new programme into programmelist
        programmeList.add(p);
        return p;
        
    }
    
    public String deleteProgFromCourse(){
        
        System.out.println("\n\n");
        System.out.println("\t ----------------------------");
        System.out.println("\t Delete Programme from Course");
        System.out.println("\t ----------------------------");
        System.out.print("\t Enter Course Code : ");
        String cCode = scanner.nextLine();
        
        return cCode;
        
    }
    
     
        public String getMenuChoice(){
          System.out.println("Welcome to Programme Management");
           System.out.println("========================================");
            System.out.println("1. Add new Programme");
            System.out.println("2. Remove Programme");
            System.out.println("3. Find Programme");
            System.out.println("4. Amend Programme Detail");
            System.out.println("5. List all Programme");
            System.out.println("6. Add Tutorial Group");
            System.out.println("7. Remove Tutorial Group");
            System.out.println("8. List all Tutorial Group");
            System.out.println("9. Generate Report");
            System.out.println("0. Exit System");
            System.out.print("Please input > ");
            String choice = scanner.nextLine();
            
            return choice;
    }
    
    public void addProgrammeUI(){
        System.out.println("Add Programme");
        System.out.println("==========================");

    }
    
    public void removeProgrammeUI(){
        System.out.println("Remove Programme");
        System.out.println("==========================");
    }
    
    public void findProgrammeUI(){
        System.out.println("Find Programme");
        System.out.println("==========================");
    }
    public void addTutorialGroupUI(){
        System.out.println("Add Tutorial Group");
        System.out.println("==========================");
    }
    public void removeTutorialGroupUI(){
        System.out.println("Remove Tutorial Group");
        System.out.println("==========================");
    }
    public void listAllTutorialGroupUI(){
        System.out.println("Tutorial Group");
        System.out.println("==========================");
    }
    public void listAllProgrammeUI(){
        System.out.println("Programme");
        System.out.println("==========================");
    }
    public void generateReportUI(){
        System.out.println("Report");
        System.out.println("===========================");
        System.out.println("Programme Code   Programme Name                      Number Of Tutorial Group");
    }   
    public void amendProgrammeDetailUI(){
        System.out.println("Amend Programme Detail");
        System.out.println("===========================");
    }
    public String amendSelection(){
        System.out.println("Select which part to amend");
        System.out.println("1. Programme Name");
        System.out.println("2. Programme Credit Hours");
        System.out.print("Please input > ");
        return scanner.nextLine();
    }
    public String inputProgrammeCode(){
        System.out.print("Enter Programme Code > ");
        return scanner.nextLine();
    }
    
    public String inputProgrammeName(){
        System.out.print("Enter Programme Name > ");
        return scanner.nextLine();
    }
    
    public int inputCreditHours(){
        System.out.print("Enter Credit Hours > ");
        int creditHours = scanner.nextInt();
        scanner.nextLine();
        return creditHours;
    }
    
    public String inputTutorialGroupName(){
        System.out.print("Enter Tutorial Group Name > ");
        return scanner.nextLine();
    }
    
    public int inputNumberOfStudent(){
        System.out.print("Enter Number of Student > ");
        int numberOfStudent = scanner.nextInt();
        scanner.nextLine();
        return numberOfStudent;
    }
    public void displayProgramme(Programme programme){
        System.out.printf("Programme Code: %s       Programme Name: %s      Credit Hours: %d\n",programme.getProgrammeCode(),programme.getProgrammeName(),programme.getCreditHours());
        
    }
    public void displayTutorial(TutorialGroup tutorial){
        System.out.printf("Group Name: %s       Number of Student: %d\n", tutorial.getGroupName(),tutorial.getNumOfStudent());
    }
    public void displayFailToFindProgramme(){
        System.out.println("Programme Not Found");
    }
    public void displayExitSystem(){
        System.out.println("Exit System");
    }
    public void displayInvalidInput(){
        System.out.println("Invalid Input");
    }
    public void displayProgrammeAddSuccess(){
        System.out.println("Programme Add Successfully");
    }
    public void displayProgrammeRemoveSuccess(){
        System.out.println("Remove Success");
    } 
    public void displayTutorialAddSuccess(){
        System.out.println("Tutorial Group Add Successfully");
    }
    public void displayFailToFindTutorial(){
        System.out.println("No Tutorial Group");
    }
    public void displayLine(){
        System.out.println("\n\n\n");
    }
    public void displayProgrammeExist(){
        System.out.println("Programme Already Exist");
    }
}
