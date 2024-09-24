/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import adt.*;
import entity.TutorialGroup;
import java.io.*;

/**
 *
 * @author howeiyoung
 */
public class TutorialGroupDAO {

    private String fileName = "tutorialGroup.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(ListInterface<TutorialGroup> productList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(productList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<TutorialGroup> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<TutorialGroup> tgList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tgList = (ArrayList<TutorialGroup>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tgList;
        }
    }
}
