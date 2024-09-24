package boundary;

import adt.*;
import entity.Tutors;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tongkitming
 */

public class TutorManagementUI {

   Scanner scanner = new Scanner(System.in);

    public int getSelection() {

        System.out.println("\nTutor Management System");
        System.out.println("1. Add Tutor");
        System.out.println("2. Remove Tutor");
        System.out.println("3. Find Tutor");
        System.out.println("4. Amend Tutor Details");
        System.out.println("5. List All Tutors");
        System.out.println("6. Filter Tutors by Subject");
        System.out.println("7. Generate Report");
        System.out.println("8. Undo to add the tutor");
        System.out.println("9. Exit");

        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        return choice;
    }

    public int update() {

        System.out.println("What you need to update ?");
        System.out.println("1.Tutor ID");
        System.out.println("2.Tutor Name");
        System.out.println("3.Subject Teaching");
        System.out.println("4.Programme");
        System.out.println("5.Salary");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;

    }

    public void listAllProducts(String outputStr) {
        System.out.println("\nList of Tutor:\n" + outputStr);
    }

    public Tutors inputTutorDetails() {

        System.out.print("Enter tutor ID: ");
        String tID = scanner.nextLine();
        System.out.print("Enter tutor name: ");
        String tName = scanner.nextLine();
        System.out.print("Enter subject: ");
        String tSubject = scanner.nextLine();
        System.out.print("Enter which programme is teaching (RDS2S1G3): ");
        String tProgramme = scanner.nextLine();
        int salary;
        while (true) {
            System.out.print("Enter Salary : ");
            if (scanner.hasNextInt()) {
                salary = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left in the buffer
                break; // Exit the loop when a valid integer is entered
            } else {
                System.out.println("Invalid input. Please enter a valid integer for salary.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return new Tutors(tID, tName, tSubject, tProgramme, salary);
    }

    public String inputTutorID() {
        System.out.print("Enter tutor ID: ");
        return scanner.nextLine();
    }

    public String inputTutorName() {
        System.out.print("Enter tutor name: ");
        return scanner.nextLine();
    }

    public String inputSubject() {
        System.out.print("Enter subject: ");
        return scanner.nextLine();
    }

    public String inputProgramme() {
        System.out.print("Enter which programme is teaching (RDS2S1G3): ");
        return scanner.nextLine();
    }

    public int inputPosition() {
        System.out.print("Enter the position that you want to delete: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputPositionID() {
        System.out.print("Enter the position that you want to amend: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int inputSalary() {
        while (true) {
            System.out.print("Enter tutor salary: ");

            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                int salary = scanner.nextInt();

                // Optionally, you can add additional validation for the salary here
                // For example, check if it's within a valid range
                return salary;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateSalaryReport(ListInterface<Tutors> tutorList) {
        int totalSalary = 0;
        int numTutors = tutorList.getNumberOfEntries();

        for (int i = 1; i <= numTutors; i++) {
            Tutors tutor = tutorList.getEntry(i);
            totalSalary += tutor.getSalary();
        }

        double averageSalary = (double) totalSalary / numTutors;

        System.out.println("\nSalary Report");
        System.out.println("Total Tutors: " + numTutors);
        System.out.println("Total Salary: " + totalSalary);
        System.out.println("Average Salary: " + averageSalary);
    }
}
