/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import adt.*;
import boundary.TutorialGroupUI;
import dao.TutorialGroupDAO;
import utility.MessageUI;
import entity.TutorialGroup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author howei
 */
public class TutorialGroupMaintainance {

    public ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    public ListInterface<TutorialGroup> sort = new ArrayList<>();
    public ListInterface<TutorialGroup> gSize = new ArrayList<>();
    public ListInterface<TutorialGroup> recordList = new ArrayList<>();
    private TutorialGroupDAO tutorialGroupDAO = new TutorialGroupDAO();
    private TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void runTutorMaintenance() {

        tutorialGroupList.add(new TutorialGroup("John Smith", 1, "johnsmith@example.com"));
        tutorialGroupList.add(new TutorialGroup("Mary Johnson", 1, "maryjohnson@example.com"));
        tutorialGroupList.add(new TutorialGroup("David Brown", 1, "davidbrown@example.com"));
        tutorialGroupList.add(new TutorialGroup("Sarah Taylor", 2, "sarahtaylor@example.com"));
        tutorialGroupList.add(new TutorialGroup("Michael Davis", 1, "michaeldavis@example.com"));
        tutorialGroupList.add(new TutorialGroup("Emily White", 2, "emilywhite@example.com"));
        tutorialGroupList.add(new TutorialGroup("William Wilson", 2, "williamwilson@example.com"));
        tutorialGroupList.add(new TutorialGroup("Olivia Harris", 3, "oliviaharris@example.com"));
        tutorialGroupList.add(new TutorialGroup("James Miller", 4, "jamesmiller@example.com"));
        tutorialGroupList.add(new TutorialGroup("Sophia Anderson", 4, "sophiaanderson@example.com"));
        tutorialGroupList.add(new TutorialGroup("haowai", 5, "haowai603@gmail.com"));
        tutorialGroupList.add(new TutorialGroup("Yao Jin", 5, "yaojin604@gmail.com"));
        tutorialGroupList.add(new TutorialGroup("Sashi", 6, "sashi603@gmail.com"));
        tutorialGroupList.add(new TutorialGroup("OmyGod", 1, "omygod604@gmail.com"));
        gSize.add(new TutorialGroup(1, 5, false));
        gSize.add(new TutorialGroup(2, 5, false));
        gSize.add(new TutorialGroup(3, 5, false));
        gSize.add(new TutorialGroup(4, 5, false));
        gSize.add(new TutorialGroup(5, 5, false));
        gSize.add(new TutorialGroup(6, 5, false));

        //to count whether each of the group is full
        groupIsFull(countStudent());
        int choice;
        do {

            choice = tutorialGroupUI.getMenuChoice();

            switch (choice) {
                case 0 :
                    MessageUI.displayExitMessage();
                    break;
                case 1 :
                    addNewStudent();
                    break;
                case 2 :
                    removeStudent();
                    break;
                case 3 :
                    changeTutorialGroup();
                    break;
                case 4 :
                    tutorialGroupUI.foundStudent(returnStudent());
                    break;
                case 5 :
                    tutorialGroupUI.listAllStudents(listAllStudents());
                    break;
                case 6 :
                    filter();
                    break;
                case 7 :
                    reportTutorialGroup();
                    break;
                default :
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    private int[] sortList() {
        int num;
        int[] numAry = new int[tutorialGroupList.getNumberOfEntries()]; // Initialize the array with the appropriate size
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            num = tutorialGroupList.getEntry(i).getNumber();
            numAry[i - 1] = num; // Store the number in the array at index i
        }
        return numAry;
    }

//main
    public static void main(String[] args) {
        TutorialGroupMaintainance tgMaintenance = new TutorialGroupMaintainance();
        tgMaintenance.runTutorMaintenance();
    }

//SUBCLASS
    private int[] countStudent() {
        int[] count = new int[10];
        int total = 0;
        for (int i = 1; i <= gSize.getNumberOfEntries(); i++) {
            for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                if (tutorialGroupList.getEntry(j).getNumber() == gSize.getEntry(i).getCgroup()) {
                    count[i]++;
                }
            }
            gSize.getEntry(i).setCurrentNo(count[i]);
        }
        return count;
    }

    private void groupIsFull(int[] count) {

        for (int i = 1; i < gSize.getNumberOfEntries(); i++) {
            if (count[i] == gSize.getEntry(i).getSize()) {
                gSize.getEntry(i).setIsFull(true);
            } else {
                gSize.getEntry(i).setIsFull(false);
            }
        }
    }
    public String findStudent() {
        boolean isValid = false;
        String name = null;

        while (!isValid) {
            name = tutorialGroupUI.inputTutorialGroupName();

            for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
                if (name.equals(tutorialGroupList.getEntry(i).getName())) {
                    
                    isValid = true;

                    break;
                } else if ("quit".equals(name)) {
                    name = null;
                    break;
                } else if (tutorialGroupList.getNumberOfEntries() == i) {
                    MessageUI.nothingFound();
                }
            }
        }
        return name;
    }
    public String returnStudent() {
        boolean isValid = false;
        String name = null;
        String string = "";

        while (!isValid) {
            name = tutorialGroupUI.inputTutorialGroupName();

            for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
                if (name.equals(tutorialGroupList.getEntry(i).getName())) {
                    string=(tutorialGroupList.getEntry(i)+"\n");
                    isValid = true;

                    break;
                } else if ("quit".equals(name)) {
                    name = null;
                    break;
                } else if (tutorialGroupList.getNumberOfEntries() == i) {
                    MessageUI.nothingFound();
                }
            }
        }
        return string;
    }
    //

//Add a student to a tutorial group
    private void addNewStudent() {
        tutorialGroupUI.addHeader();
String lastList = "";
        for (int i = 1; i < gSize.getNumberOfEntries(); i++) {
            if (!gSize.getEntry(i).isIsFull()) {
                TutorialGroup newStudent = tutorialGroupUI.inputTutorialGroupDetails(i);
                tutorialGroupList.add(newStudent);
                lastList = (tutorialGroupList.getEntry(tutorialGroupList.getNumberOfEntries()) + "\n");
                break;
            }
tutorialGroupDAO.saveToFile(tutorialGroupList);
            
        }
        String formattedDateTime = currentDateTime.format(formatter);
        recordList.add(new TutorialGroup("add", lastList, formattedDateTime));
        tutorialGroupList.bubbleSort(sortList());
    }

    //Remove a student from a tutorial group
    public void removeStudent() {
        String record = "";
        tutorialGroupUI.listAllStudents(listAllStudents());
        tutorialGroupUI.removeHeader();
        String name = findStudent();
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            if (name.equals(tutorialGroupList.getEntry(i).getName())) {
                if (tutorialGroupUI.comfirmationRemove(tutorialGroupList.getEntry(i))) {
                    record = (tutorialGroupList.getEntry(i) + "\n");
                    tutorialGroupList.remove(i);
                }
            }


//            tutorialGroupUI.listAllStudents(listAllStudents());
        }
             String formattedDateTime = currentDateTime.format(formatter);
            recordList.add(new TutorialGroup("remove", record, formattedDateTime));
    }

//Change the tutorial group for a student.
    public void changeTutorialGroup() {
        String record = "";
        
        tutorialGroupUI.listAllStudents(listAllStudents());
        tutorialGroupUI.changeHeader();
        String name = findStudent();
        boolean isValid = false;
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            if (name.equals(tutorialGroupList.getEntry(i).getName())) {
                int num = 1;
                int pInt = 0;
                while (!isValid) {
                    num = tutorialGroupUI.changeGroup(tutorialGroupList.getEntry(i), gSize.getNumberOfEntries());
                    if (num == 0) {
                        break;
                    }
                    pInt = tutorialGroupList.getEntry(i).getNumber();
                    if (gSize.getEntry(num).isIsFull()) {
                        MessageUI.isFull();
                    } else {
                        isValid = true;
                    }
                }
                if (!(pInt == 0)) {
                    tutorialGroupList.getEntry(i).setNumber(num);
                    tutorialGroupUI.displayChangeGroup(tutorialGroupList.getEntry(i), pInt);
                    record = (tutorialGroupUI.returnChangeGroup(tutorialGroupList.getEntry(i), pInt) + "\n");
                    groupIsFull(countStudent());
                    tutorialGroupList.bubbleSort(sortList());
                }
            }
        }
        String formattedDateTime = currentDateTime.format(formatter);
        recordList.add(new TutorialGroup("change", record, formattedDateTime));
    }

//Find a student in a tutorial group
//    public void foundStudent() {
//        tutorialGroupUI.listAllStudents(listAllStudents());
//        
//        String name = findStudent();
//        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
//            System.out.println(tutorialGroupList.getEntry(i));
//        }
//    }

//List all students in a tutorial group    
    public String listAllStudents() {
        String outputStr = "";
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {

            outputStr += tutorialGroupList.getEntry(i) + "\n";
        }
        return outputStr;
    }

//
    public String listStudents() {
        String outputStr = "";

        int choice = tutorialGroupUI.chooseGroup(gSize.getNumberOfEntries());
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            if (tutorialGroupList.getEntry(i).getNumber() == choice) {
                outputStr += tutorialGroupList.getEntry(i) + "\n";
            }
        }
        tutorialGroupUI.headerListStudent(choice);
        return outputStr;
    }

    public String listGroups() {
        String outputStr = "";

        for (int i = 1; i <= gSize.getNumberOfEntries(); i++) {
            outputStr += gSize.getEntry(i).customToString() + "\n";
        }
        return outputStr;
    }

//Filter tutorial groups based on criteria
    public void filter() {
        tutorialGroupUI.filterHeader();
        int choice;
        do {
            choice = tutorialGroupUI.getChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1 :
                    filterGroup();
                    break;
                case 2 :
                    filterStudent();
                    break;
                default :
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);

    }

    public void filterGroup() {
        String record = "";

        tutorialGroupUI.listGroups(listGroups());
        int choice = tutorialGroupUI.filterGroup();
        for (int i = 1; i <= gSize.getNumberOfEntries(); i++) {
            if (gSize.getEntry(i).getCgroup() == choice) {
                int pInt = gSize.getEntry(i).getSize();
                gSize.getEntry(i).setSize(tutorialGroupUI.changeSize());
                if (!(pInt == 0)) {
                    tutorialGroupUI.displayChangeSize(i, pInt, gSize.getEntry(i).getSize());
                    record = tutorialGroupUI.recordChangeSize(i, pInt, gSize.getEntry(i).getSize());
                }
            }

        }
        String formattedDateTime = currentDateTime.format(formatter);
        recordList.add(new TutorialGroup("filter", record, formattedDateTime));
    }

    public void filterStudent() {
        String record = "";
        tutorialGroupUI.listStudent(listStudents());

        String name = findStudent();

        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            if (tutorialGroupList.getEntry(i).getName().equals(name)) {

                TutorialGroup filterStudent = tutorialGroupUI.filterStudent(i);

                tutorialGroupList.replace(i, filterStudent);
                tutorialGroupUI.displayFilterStudent(name, filterStudent.getName());
                record = tutorialGroupUI.recordFilterStudent(name, filterStudent.getName());
            }
        }
        String formattedDateTime = currentDateTime.format(formatter);
        recordList.add(new TutorialGroup("filter", record, formattedDateTime));
    }

    public String listReport() {
        String outputStr = "";

        for (int i = 1; i <= gSize.getNumberOfEntries(); i++) {
            outputStr += gSize.getEntry(i).customToString() + "     " + gSize.getEntry(i).currentNo() + "\n";
        }
        return outputStr;
    }

    public String record() {
        String outputStr = "";
        for (int i = 1; i <= recordList.getNumberOfEntries(); i++) {
            outputStr += recordList.getEntry(i).record() + "\n";
        }
        return outputStr;
    }

    public void reportTutorialGroup() {
        tutorialGroupUI.listReport(listReport(), tutorialGroupList.getNumberOfEntries(),record());
    }
}
