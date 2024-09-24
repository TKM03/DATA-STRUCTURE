package dao;

import adt.LinkedStack;
import adt.StackInterface;
import entity.Tutors;

/**
 *
 * @author Tongkitming
 */
public class TutorInitializeS {

    public StackInterface<Tutors> initializeTutorsS() {
        StackInterface<Tutors> tutorStack = new LinkedStack<>();

        
        tutorStack.push(new Tutors("1006","Tok","DATA STRUCTURE", "RDS2S1G1", 10000));
        tutorStack.push(new Tutors("1005", "Tmk", "DATA STRUCTURE", "RDS2S1G5", 9000));
        tutorStack.push(new Tutors("1004", "Tkm", "STATISTIC", "RDS2S1G4", 4000));
        tutorStack.push(new Tutors("1003", "Abu", "DATA STRUCTURE", "RDS2S1G3", 5000));
        tutorStack.push(new Tutors("1002", "Ali", "DATA SCIENCE", "RDS2S1G2", 7000));
        tutorStack.push(new Tutors("1001", "Twh", "DATA STRUCTURE", "RDS2S1G1", 6000));
        return tutorStack;
    }

}
