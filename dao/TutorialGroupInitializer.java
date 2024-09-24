/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import adt.*;
import entity.TutorialGroup;
/**
 *
 * @author howeiyoung
 */
public class TutorialGroupInitializer {
    //  Method to return a collection of with hard-coded entity values
  public ListInterface<TutorialGroup> initializeTutorialGroup() {
    ListInterface<TutorialGroup> pList = new ArrayList<>();

    pList.add(new TutorialGroup("neoclay", 2, "howeiyong6020@gmail.com"));
    pList.add(new TutorialGroup("neoclay", 2, "howeiyong6020@gmail.com"));
    pList.add(new TutorialGroup("neoclay", 3, "howeiyong6020@gmail.com"));
    pList.add(new TutorialGroup("neoclay", 4, "howeiyong6020@gmail.com"));
    pList.add(new TutorialGroup("neoclay", 5, "howeiyong6020@gmail.com"));
//    pList.add(new Tutor("A1002", "Pen", 50));
//    pList.add(new Tutor("A1003", "Pencil", 30));
//    pList.add(new Tutor("A1004", "Notepad", 40));
//    pList.add(new Tutor("A1005", "Ruler", 15));
//    pList.add(new Tutor("A1006", "Eraser", 60));
    return pList;
  }

  public static void main(String[] args) {
    // To illustrate how to use the initializeProducts() method
    TutorialGroupInitializer t = new TutorialGroupInitializer();
    ListInterface<TutorialGroup> tutorialGroupList = t.initializeTutorialGroup();
    System.out.println("\nTutors:\n" + tutorialGroupList);
  }
}
