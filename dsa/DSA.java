/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;
import control.CourseMaintenance;
import control.ProgrammeManagement;
import control.TutorialGroupMaintainance;
import control.TutorManagementSystem;
import control.AssignmentTeamManagement;

import java.util.Scanner;
/**
 *
 * @author Fong Wei Sheng
 */
public class DSA {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        String choice;
do{
        System.out.println("\n\nWelcome to University Management System");
        System.out.println("=======================================================");
        System.out.println("1. Programme Management");
        System.out.println("2. Course Management");
        System.out.println("3. Tutorial Group Management");
        System.out.println("4. Tutor Management");
        System.out.println("5. Assignment Team Management");
        System.out.println("0. Exit System");
        System.out.print("Please input > ");
        choice = scanner.nextLine();
        System.out.println("\n\n");
        switch(choice){
            case "1":
                new ProgrammeManagement().programmeManagement();
                break;
            case "2":
                new CourseMaintenance().runCourseMaintenance();
                break;
            case "3":
                new TutorialGroupMaintainance().runTutorMaintenance();
                break;
            case "4":
                new TutorManagementSystem().runTutorManager();
                break;
            case "5":
                new AssignmentTeamManagement().menu();
            case "0":
                System.out.println("Program Exited");
                break;
            default:
                System.out.println("Invalid Input\n");
        }
    }while(!choice.equals("0"));
    }
}
    