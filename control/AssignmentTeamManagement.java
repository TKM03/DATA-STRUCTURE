package control;
/**
 *
 * @author Low Su Yin 
 */
//import java.util.ArrayList;
import adt.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AssignmentTeamManagement {

    private Scanner scanner = new Scanner(System.in);

    public class Student {

        private String studentName;
        private String studentId;

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public Student(String studentName, String studentId) {
            this.studentName = studentName;
            this.studentId = studentId;
        }

    }

    public class AssignmentTeam {

        private String teamName;
        private ArrayList<Student> students;
        private static final int MAX_TEAM_SIZE = 5;

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public ArrayList<Student> getStudents() {
            return students;
        }

        public void setStudents(ArrayList<Student> students) {
            this.students = students;
        }

        public AssignmentTeam(String teamName) {
            this.teamName = teamName;
            this.students = new ArrayList<>();
        }

        public boolean addStudent(Student student) {
            if (students.getNumberOfEntries() < MAX_TEAM_SIZE) {
                students.add(student);
                return true;
            }
            return false;
        }
    }

    public static void saveTeamsToFile(ArrayList<AssignmentTeam> teams, String fileName) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < teams.getNumberOfEntries(); i++) {
                AssignmentTeam team = teams.getEntry(i + 1);
                writer.println("Team: " + team.getTeamName());

                ArrayList<Student> students = team.getStudents();
                for (int j = 0; j < students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j + 1);
                    writer.println("Student: " + student.getStudentName() + "," + student.getStudentId());
                }
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load from text file
    public ArrayList<AssignmentTeam> loadTeamsFromFile(String fileName) {
        ArrayList<AssignmentTeam> Teams = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentTeamName = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Team: ")) {
                    currentTeamName = line.substring(6); // Extract the team name
                    Teams.add(new AssignmentTeam(currentTeamName));
                } else if (line.startsWith("Student: ") && currentTeamName != null) {
                    String studentInfo = line.substring(9); // Extract the student info
                    String[] parts = studentInfo.split(",");
                    if (parts.length == 2) {
                        String studentName = parts[0];
                        String studentId = parts[1];
//                    int studentPhone = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
                        Teams.getEntry(Teams.getNumberOfEntries()).addStudent(new Student(studentName, studentId));
                    }
                }
            }
            System.out.println("Data loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Teams;
    }

    //display all
    public static void display(ArrayList<AssignmentTeam> Teams) {

        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            System.out.println("\n" + (i+1) + ". " + "Team: " + team.getTeamName());

            ArrayList<Student> students = team.getStudents();
            System.out.println("Student(s): ");
            for (int j = 0; j < students.getNumberOfEntries(); j++) {
                Student student = students.getEntry(j + 1);
                System.out.println("\t" + (j+1) + ". " + student.getStudentName() + "(ID: " + student.getStudentId() + ")");
            }
        }
    }

    public void createTeam() {
        ArrayList<AssignmentTeam> Teams = new ArrayList<>();
        Teams = loadTeamsFromFile("team.txt");

        while (true) {
            System.out.print("Enter student name or 'exit' to finish: ");
            String studentName = scanner.nextLine();

            if (studentName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter student's ID: ");
            String studentId = scanner.nextLine();

            System.out.println("\nEnter team name: ");
            String teamName = scanner.nextLine();

            Student student = new Student(studentName, studentId);
            boolean teamExists = false;

            // team exists?
            AssignmentTeam existingTeam = null;
            for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
                AssignmentTeam t = Teams.getEntry(i + 1);
                if (t.getTeamName().equalsIgnoreCase(teamName)) {
                    t.addStudent(student);
                    teamExists = true;
                    break;
                }
            }
            // If not, create new one
            if (!teamExists) {
                AssignmentTeam newTeam = new AssignmentTeam(teamName);
                newTeam.addStudent(student);
                Teams.add(newTeam);
            } else {
                // team full?
                if (existingTeam != null && existingTeam.getStudents().getNumberOfEntries() < AssignmentTeam.MAX_TEAM_SIZE) {
                    existingTeam.addStudent(student);
                } else {
                    System.out.println("Team '" + teamName + "' is already full.");
                }
            }
        }
        saveTeamsToFile(Teams, "team.txt");
        display(Teams);

    }

    public void removeTeamByName(ArrayList<AssignmentTeam> loadedTeams, String teamName) {
        loadTeamsFromFile("team.txt");
        for (int i = 0; i < loadedTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = loadedTeams.getEntry(i + 1);
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                loadedTeams.remove(i + 1);
                System.out.println("Team '" + teamName + "' removed.");
                removeAllStudents(loadedTeams, teamName);
                saveTeamsToFile(loadedTeams, "team.txt");
                display(loadedTeams);
                return; 
            }
        }
        System.out.println("Team '" + teamName + "' not found.");
    }

    public static void removeAllStudents(ArrayList<AssignmentTeam> Teams, String teamName) {
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam Team = Teams.getEntry(i + 1);
            if (Team.getTeamName().equalsIgnoreCase(teamName)) {
                Team.getStudents().clear(); // Remove all students from the team
            }
        }
    }

    public void amendTeamName(ArrayList<AssignmentTeam> Teams, String oldTeamName, String newTeamName) {
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            if (team.getTeamName().equalsIgnoreCase(oldTeamName)) {
                team.setTeamName(newTeamName);
                System.out.println("Team name amended from '" + oldTeamName + "' to '" + newTeamName + "'.");
                saveTeamsToFile(Teams, "team.txt");
                display(Teams);
                return; // Exit the function once the team name is amended
            }
        }
        System.out.println("Team '" + oldTeamName + "' not found.");
    }

    public void addStudentToTeam() {
        ArrayList<AssignmentTeam> Teams = new ArrayList<>();
        Teams = loadTeamsFromFile("team.txt");
        display(Teams);
        
        while (true) {
            System.out.println("\nEnter team name or 'exit' to finish: ");
            String teamName = scanner.nextLine();

            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();

            if (studentName.equalsIgnoreCase("exit") || teamName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter student's ID: ");
            String studentId = scanner.nextLine();

            Student student = new Student(studentName, studentId);
            boolean teamExists = false;

            // Check if the team already exists
            AssignmentTeam existingTeam = null;
            for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
                AssignmentTeam t = Teams.getEntry(i + 1);
                if (t.getTeamName().equalsIgnoreCase(teamName)) {
                    teamExists = true;
                    existingTeam = t;
                    break;
                }
            }

            if (!teamExists) {
                AssignmentTeam newTeam = new AssignmentTeam(teamName);
                if (newTeam.getStudents().getNumberOfEntries() < AssignmentTeam.MAX_TEAM_SIZE) {
                    newTeam.addStudent(student);
                    Teams.add(newTeam);
                } else {
                    System.out.println("Team '" + teamName + "' is already full.");
                }
            } else {
                if (existingTeam != null && existingTeam.getStudents().getNumberOfEntries() < AssignmentTeam.MAX_TEAM_SIZE) {
                    existingTeam.addStudent(student);
                } else {
                    System.out.println("Team '" + teamName + "' is already full.");
                }
            }
        }
        saveTeamsToFile(Teams, "team.txt");
        display(Teams);
    }

    public void removeStudent(ArrayList<AssignmentTeam> Teams, String teamName, String studentName) {
        loadTeamsFromFile("team.txt"); //load teams
        
        display(Teams);
        // Find team
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                ArrayList<Student> students = team.getStudents();

                for (int j = 0; j < students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j+1);
                    if (student.getStudentName().equals(studentName)) {
                        students.remove(j+1);
                        System.out.println("Student: " + studentName + " removed from team '" + teamName + "'.");
                        saveTeamsToFile(Teams, "team.txt");
                        display(Teams);
                        return; // Student found and removed, exit the loop
                    }
                }
                System.out.println("Student: " + studentName + " not found in team '" + teamName + "'.");
                return; // Student not found, exit the function
            }
        }
        System.out.println("Team '" + teamName + "' not found.");
    }

    public void searchTeamByMemberCount(ArrayList<AssignmentTeam> Teams, int memberCount) {
        System.out.println("Teams with " + memberCount + " members:");

        boolean found = false;
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            if (team.getStudents().getNumberOfEntries() == memberCount) {
                display(Teams);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No teams found with " + memberCount + " members.");
        }
    }

    //team only
    public void listTeamNames(ArrayList<AssignmentTeam> Teams) {
        System.out.println("List of Team Names:");
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            System.out.println((i+1) + ". " + team.getTeamName());
        }
    }

    //student only for one team
    public void listTeamStudents(ArrayList<AssignmentTeam> Teams, String teamName) {
        for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = Teams.getEntry(i + 1);
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                System.out.println("Students in Team '" + teamName + "':");
                ArrayList<Student> students = team.getStudents();
                for (int j = 0; j < students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j + 1);
                    System.out.println("\t" + (j+1) + ". " + student.getStudentName() + "(ID: " + student.getStudentId() + ")");
                }
                return;
            }
        }
        System.out.println("Team '" + teamName + "' not found.");
    }

    public void generateTeamStatusReport(ArrayList<AssignmentTeam> Teams) {
    int fullTeams = 0;
    int notFullTeams = 0;

    System.out.println("Team Status Report:");
    System.out.println("===================");

    for (int i = 0; i < Teams.getNumberOfEntries(); i++) {
        AssignmentTeam team = Teams.getEntry(i+1);
        int teamSize = team.getStudents().getNumberOfEntries();
        System.out.println("Team: " + team.getTeamName());
        System.out.println("Team Size: " + teamSize);

        if (teamSize == AssignmentTeam.MAX_TEAM_SIZE) {
            System.out.println("Status: Full");
            fullTeams++;
        } else {
            System.out.println("Status: Not Full");
            notFullTeams++;
        }

        System.out.println("-------------------");
    }

    int totalTeams = fullTeams + notFullTeams;
    double fullPercentage = (double) fullTeams / totalTeams * 100;
    double notFullPercentage = (double) notFullTeams / totalTeams * 100;

    System.out.println("Full Teams: " + fullTeams + " (" + fullPercentage + "%)");
    System.out.println("Not Full Teams: " + notFullTeams + " (" + notFullPercentage + "%)");
}
    
    public void menu() {
        int choice = 0;
        do {
            System.out.println("\nASSIGNMENT TEAM MANAGEMENT SUBSYSTEM");
            System.out.println("1. Create a team.");
            System.out.println("2. Remove a team.");
            System.out.println("3. Amend assignment name.");
            System.out.println("4. Add student to team.");
            System.out.println("5. Remove student from team.");
            System.out.println("6. Search for assignment team based on number of members.");
            System.out.println("7. List assignment teams.");
            System.out.println("8. List students under an assignment team.");
            System.out.println("9. Generate team status report.");
            System.out.println("10. Exit program.");

            System.out.println("\nEnter a choice (1...9): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            ArrayList<AssignmentTeam> Teams = loadTeamsFromFile("team.txt");
            switch (choice) {
                case 1:
                    createTeam();
                    break;

                case 2:
                    System.out.print("Enter the name of the team to remove: ");
                    String teamToRemove = scanner.nextLine();
                    removeTeamByName(Teams, teamToRemove);
                    break;

                case 3:
                    System.out.print("Enter the name of the team to amend: ");
                    String teamChange = scanner.nextLine();
                    System.out.print("Enter the new team name: ");
                    String newTeamName = scanner.nextLine();
                    amendTeamName(Teams, teamChange, newTeamName);
                    break;

                case 4:
                    addStudentToTeam();
                    break;

                case 5:
                    System.out.println("Enter the team name: ");
                    String teamName = scanner.nextLine();
                    System.out.println("Enter student name to remove: ");
                    String studentName = scanner.nextLine();
                    removeStudent(Teams, teamName, studentName);
                    break;

                case 6:
                    System.out.print("Enter the number of members to search for: ");
                    int member = scanner.nextInt();
                    scanner.nextLine();
                    searchTeamByMemberCount(Teams, member);
                    break;

                case 7:
                    listTeamNames(Teams);
                    break;

                case 8:
                    System.out.println("Enter the team name to list members: ");
                    String teamName2 = scanner.nextLine();
                    listTeamStudents(Teams, teamName2);
                    break;

                case 9:
                    generateTeamStatusReport(Teams);
                    break;

                default:
                    System.out.println("\nExiting program.");
            }
        } while (choice >= 1 && choice <= 9);
    }

   
}
