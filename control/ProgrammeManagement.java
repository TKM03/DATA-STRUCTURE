package control;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fong Wei Sheng
 */
import adt.ArrayList;
import adt.ListInterface;
import java.util.Scanner;
import entity.Programme;
import entity.TutorialGroup;
import boundary.ProgrammeMaintenanceUI;
import java.util.Iterator;

public class ProgrammeManagement {
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ProgrammeMaintenanceUI programmeUI = new ProgrammeMaintenanceUI();
    
    public void programmeManagement(){
        tutorialGroupList.add(new TutorialGroup("G1",23));
        tutorialGroupList.add(new TutorialGroup("G2",25));
        programmeList.add(new Programme("DCS", "Diploma in Computer Science", 32));
        programmeList.add(new Programme("RDS","Bachelor Degree in Data Science", 31, new ArrayList<>()));
        programmeList.getEntry(1).setTutorialGroups(tutorialGroupList);
        String choice;
        do{
            choice = programmeUI.getMenuChoice();
            switch(choice){
                case "1":
                    programmeUI.displayLine();
                    addProgramme();
                    programmeUI.displayLine();
                    break;
                case "2":
                    programmeUI.displayLine();
                    removeProgramme();
                    programmeUI.displayLine();
                    break;
                case "3":
                    programmeUI.displayLine();
                    findProgramme();
                    programmeUI.displayLine();
                    break;
                case "4":
                    programmeUI.displayLine();
                    amendProgrammeDetail();
                    programmeUI.displayLine();
                    break;
                case "5":
                    programmeUI.displayLine();
                    listAllProgramme();
                    programmeUI.displayLine();
                    break;
                case "6":
                    programmeUI.displayLine();
                    addTutorialGroup();
                    programmeUI.displayLine();
                    break;
                case "7":
                    programmeUI.displayLine();
                    removeTutorialGroup();
                    programmeUI.displayLine();
                    break;
                case "8":
                    programmeUI.displayLine();
                    listAllTutorialGroup();
                    programmeUI.displayLine();
                    break; 
                case "9":
                    programmeUI.displayLine();
                    generateReport();
                    programmeUI.displayLine();
                    break;
                case "0":
                    programmeUI.displayLine();
                    programmeUI.displayExitSystem();
                    programmeUI.displayLine();
                    break;
                default:
                    programmeUI.displayInvalidInput();
                    break;
            }
        }while(!choice.equals("0"));
    }

    private void addProgramme() {
        programmeUI.addProgrammeUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        String programmeName = programmeUI.inputProgrammeName();
        int creditHours = programmeUI.inputCreditHours();
        
        if(programmeList.getEntry(new Programme(programmeCode))==null){
        Programme programme = new Programme(programmeCode, programmeName, creditHours, new ArrayList<>());
        programmeList.add(programme);
        programmeUI.displayProgrammeAddSuccess();
        }else{
            programmeUI.displayProgrammeExist();
        }
        
    }

    private void removeProgramme() {
        programmeUI.removeProgrammeUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        int position = programmeList.getPosition(new Programme(programmeCode));
        if(programmeList.remove(position)!=null){
            programmeUI.displayProgrammeRemoveSuccess();
        }else{
            programmeUI.displayFailToFindProgramme();
        }
    }

    private void findProgramme() {
        programmeUI.findProgrammeUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        
        Programme programmeToFind = programmeList.getEntry(new Programme(programmeCode));
        if(programmeToFind != null){
            programmeUI.displayProgramme(programmeToFind);
        }else{
            programmeUI.displayFailToFindProgramme();
        }
        
    }

    private void amendProgrammeDetail() {
        programmeUI.amendProgrammeDetailUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        String choice = programmeUI.amendSelection();
        switch (choice){
            case "1":
                String programmeName = programmeUI.inputProgrammeName();
                programmeList.getEntry(new Programme(programmeCode)).setProgrammeName(programmeName);
       
                break;
            case "2":
                int creditHours = programmeUI.inputCreditHours();
                programmeList.getEntry(new Programme(programmeCode)).setCreditHours(creditHours);
                break;
            default:
                programmeUI.displayInvalidInput();
        }
    }

    private void listAllProgramme() {
        programmeUI.listAllProgrammeUI();
        for(int i=0; i<programmeList.getNumberOfEntries(); i++){
            programmeUI.displayProgramme(programmeList.getEntry(i+1));
        }   
    }

    private void addTutorialGroup() {
        programmeUI.addTutorialGroupUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        if(programmeList.getEntry(new Programme (programmeCode))!=null){
            String tutorialName = programmeUI.inputTutorialGroupName();
            int numberOfStudent = programmeUI.inputNumberOfStudent();
            TutorialGroup tutorialGroup = new TutorialGroup(tutorialName,numberOfStudent);
            programmeList.getEntry(new Programme (programmeCode)).getTutorialGroups().add(tutorialGroup);
            programmeUI.displayTutorialAddSuccess();
        }else{
            programmeUI.displayFailToFindProgramme();
        }
    }

    private void removeTutorialGroup() {
        programmeUI.removeTutorialGroupUI();
        String programmeCode = programmeUI.inputProgrammeCode();
        Programme programmeToFind = programmeList.getEntry(new Programme(programmeCode));
        
        if(programmeToFind!=null){
            String tutorialName = programmeUI.inputTutorialGroupName();
            for(int i=0; i<programmeToFind.getTutorialGroups().getNumberOfEntries(); i++){
                if(programmeToFind.getTutorialGroups().getEntry(i+1).getGroupName().equals(tutorialName)){
                    programmeToFind.getTutorialGroups().remove(i+1);
                }
            }
        }else{
            programmeUI.displayFailToFindProgramme();
        }
    }

    private void listAllTutorialGroup() {
        programmeUI.listAllTutorialGroupUI();
        String programmeCode = programmeUI.inputProgrammeCode();
          Programme programmeToFind = programmeList.getEntry(new Programme(programmeCode));
        
        if(programmeToFind!=null){
            if(programmeToFind.getTutorialGroups()!= null){
            for(int i=0; i<programmeToFind.getTutorialGroups().getNumberOfEntries(); i++){
                programmeUI.displayTutorial(programmeToFind.getTutorialGroups().getEntry(i+1));
            }
            }else{
                programmeUI.displayFailToFindTutorial();
            }
        }else{
            programmeUI.displayFailToFindProgramme();
        }
    }
    
    private void generateReport() {
        programmeUI.generateReportUI();
        Iterator<Programme> iterator = programmeList.getIterator();
        while(iterator.hasNext()){
            Programme programme = iterator.next();
            int total;
            if (programme.getTutorialGroups() == null){
                total = 0;
            }else{
                total = programme.getTutorialGroups().getNumberOfEntries();
            }
            
            System.out.printf("%-16s %-35s %-4d\n",programme.getProgrammeCode(),programme.getProgrammeName(),total);
        }
    }   
    
    
    
 

    
   
}
