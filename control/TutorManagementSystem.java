package control;

import adt.*;
import boundary.TutorManagementUI;

import entity.Tutors;

import dao.*;

import java.util.Scanner;

/**
 *
 * @author Tongkitming
 */

public class TutorManagementSystem {

    public void runTutorManager() {

        TutorManagementUI ui = new TutorManagementUI();
        TutorInitialize t = new TutorInitialize();
        TutorInitializeS tutor = new TutorInitializeS();
        ListInterface<Tutors> tutorList = t.initializeTutors();
        Scanner scanner = new Scanner(System.in);
        UndoRedoManager<ListInterface<Tutors>> undoRedoManager = new UndoRedoManager<>();
        StackInterface<Tutors> tutorStack = tutor.initializeTutorsS();
        StackInterface<Tutors> previousStateUndo = new LinkedStack<>();
        previousStateUndo.clear();
        
        int choice;
        do {
            choice = ui.getSelection();
            switch (choice) {
                case 1:

                    Tutors newTutor = ui.inputTutorDetails();

                    if (tutorList.add(newTutor)) {
                        tutorStack.push(newTutor);
                        previousStateUndo.push(newTutor);
                        System.out.println("Tutor added successfully.");
                    } else {

                        System.out.println("Failed to add tutor. Maximum capacity reached.");
                    }

                    break;
                case 2:

                    int positionToDelete = ui.inputPosition();
                    ListInterface<Tutors> previousList = tutorList;
                    if (tutorList.remove(positionToDelete) != null) {

                        System.out.println("Tutor deleted successfully.");
                    } else {
                        System.out.println("Failed to delete tutor.");
                    }

                    break;

                case 3:
                    String tutorName = ui.inputTutorName();
                    Tutors foundTutor = tutorList.find(tutorName);

                    if (foundTutor != null) {
                        System.out.println("Found Tutor: " + foundTutor);
                    } else {
                        System.out.println("Tutor not found.");
                    }

                    break;

                case 4:
                    tutorList.listAll();

                    int positionToReplace =ui.inputPositionID();

                    Tutors newTutorData = ui.inputTutorDetails();
                    if (tutorList.replace(positionToReplace, newTutorData)) {
                        System.out.println("Tutor replaced successfully.");
                    } else {
                        System.out.println("Failed to replace tutor.");
                    }

                    break;

                case 5:

                    System.out.println("\nTotal tutor : " + tutorList.getNumberOfEntries() + "\nAll tutors:\n" + tutorList);
                    ui.promptEnterKey();
                    break;
                case 6:
                    String filterSubject = ui.inputSubject();
                    tutorList.filter(filterSubject);
                    break;
                case 7:
                    ui.generateSalaryReport(tutorList);
                    break;

                case 8:
                    if (previousStateUndo.getNumOfEntry()> 0 && previousStateUndo.getNumOfEntry() < 2) {
                        tutorList.clear();
                        tutorStack.pop();
                        while (tutorStack.getNumOfEntry()!= 0)
                        tutorList.add(tutorStack.pop());
                        tutorStack = tutor.initializeTutorsS();
                        tutorList.listAll();
                        previousStateUndo.pop();
                        System.out.println("Undo successful.");
                    } else {
                        System.out.println("Cannot to undo please remove the tutor");
                    } 
                    break;

                case 9:

                    System.out.println("Done Exit Tutor Management System");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        } while (choice != 9);

    }

}
