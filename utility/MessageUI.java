/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author YU YUNG JUN/ Ho Wei Young
 */
public class MessageUI {
    
    public static void displayInvalidNumMessage() {
        System.out.println("\n\t\t --- The number you have entered is INVALID ! ---");
    }
    
    public static void displayExitMessage() {
        System.out.println("\nExit System...");
    }
    
    public static void displayRetypeMessage() {
        System.out.println("\n\t\t Please Enter Again...");
    }
    
    public static void displayCourseAddedMessage(){
        System.out.println("\n\t\t--- Course Added Successfully !---\n");
    }
    
    public static void displayCourseExistMessage(){
        System.out.println("\n\t\t--- This course already exist !---\n");
    }
    
    public static void displayCourseDeletedMessage(){
        System.out.println("\n\t\t --- Course Deleted Successfully ! ---");
    }
    
    public static void displayCourseModifiedMessage(){
        System.out.println("\n\t\t --- Course Modified Successfully ! ---");
    }
    
    public static void displayNoCourseFoundMessage(){
        System.out.println("\n\t\t --- No Course Found ! ---");
    }
    
    public static void displayProgDeletedMessage(){
        System.out.println("\n\t\t --- Programme Deleted Successfully ! ---");
    }
    
    public static void displayProgAddedMessage(){
        System.out.println("\n\t\t--- Programme Added Successfully !---\n");
    }
    
    public static void displayInvalidChoiceMessage() {
    System.out.println("\nInvalid choice");
  }
    public static void nothingFound(){
      System.out.println("\nNothing found !!!");
  }
     public static void isFull(){
      System.out.println("\nIt is Full !!!");
  }
}
