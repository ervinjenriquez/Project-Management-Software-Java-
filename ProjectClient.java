import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ProjectClient {

    static Project project; //Current Project
    static Scanner user = new Scanner(System.in); //Input
    static boolean loop = true;
    static ArrayList<Project> list = new ArrayList<Project>();

    public static void main(String[] args) {
        while (loop) {
            menu();
            System.out.println("Enter a command from the list above (q to quit): \nA project must be selected before modifying it.");
            String command = user.next();

            switch (command) {
                case "a":
                    add();
                    break;
                case "st":
                    selectCurrent();
                    break;
                case "sc":
                    showCurrent();
                    break;
                case "d":
                    delete();
                    break;
                case "sl":
                    showList();
                    break;
                case "sd":
                    showDetails();
                    break;
                case "nt":
                    changeName();
                    break;
                case "ht":
                    changeHours();
                    break;
                case "dt":
                    changeDescription();
                    break;
                case "rt":
                    changeReservation();
                    break;
                case "q":
                    quit();
                    break;
                default:
                    System.out.println("Command not recognized.");
            }
        }
    }

    public static void menu() {
        System.out.println("\n	a	Add a task.");
        System.out.println("	d	Delete a task.");
        System.out.println("	nt	Change the name of a task.");
        System.out.println("	dt	Change the description of a task.");
        System.out.println("	ht	Change the hours of a task.");
        System.out.println("	rt	Change the reservation status of a task.");
        System.out.println("	st	Select a project from the list to be modified.");
        System.out.println("    sd  Show the details of a project.");
        System.out.println("    sc  Show project currently selected.");
        System.out.println("    sl  Show the current list of projects.");
        System.out.println("	q	Quit.\n");
    }

    public static void selectCurrent() {
        if (list.size() == 0) {
            System.out.println("Sorry, there are currently no projects. Please create a project.");
        } else {
            System.out.println("Enter the name of the project you want.");
            String title = user.next();
            System.out.println("Enter the version number attached to that project. (Use command sl to see what version is attached to it)");
            int version = user.nextInt();
            System.out.println("Searching...");

            for (int i = 0; i < list.size(); i++) {
                if (title.equalsIgnoreCase(list.get(i).getName()) && version == list.get(i).getVersion()) {
                    project = list.get(i);
                    System.out.println("Project found & selected.");
                }
            }
        }
    }

    public static void showCurrent() {
        if (project == null) {
            System.out.println("Sorry, there are currently no projects. Please create a project.");
        } else {
            System.out.println("The current project is : " + project.getName() + "." + project.getVersion());
        }
    }

    public static void showDetails() {
        if (project == null) {
            System.out.println("Sorry, there are currently no projects. Please create a project.");
        } else {
            System.out.println("Name: " + project.getName());
            System.out.println("Version: " + project.getVersion());
            System.out.println("Description: " + project.getDescription());
            System.out.println("Hours: " + project.getHours());
            System.out.println("Reserved: " + project.isReserved());
        }
    }

    public static void showList() {

        if (list.size() == 0) {
            System.out.println("Sorry, there are currently no projects. Please create a project.");
        } else {
            System.out.println("\nThere are " + list.size() + " projects in total.");
            System.out.println("[LIST START]");
            for (int i = 0; i < list.size(); i++) {
                if (i % 3 == 0 && i != 0) {
                    System.out.print("\n" + list.get(i).getName() + "." + list.get(i).getVersion() + ", ");
                } else {
                    System.out.print(list.get(i).getName() + "." + list.get(i).getVersion() + ", ");
                }
            }
            System.out.println("\n[LIST END]");
        }
    }

    public static void add() {
        System.out.println("What is the name of the task?");
        user.nextLine();
        String name = user.nextLine();

        if (version(name) > 0) {
            System.out.println("This name already exists");
            System.out.println("Would you like to create a 'versioned' project? (Y/N)");
            String n = user.next();

            if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {
                version(name);
                System.out.println("The name will now be, '" + name + "." + version(name) + "'");

                user.nextLine();
                System.out.println("What is the description of the task?");
                String description = user.nextLine();
                System.out.println("How many hours does the task allocate?");
                int hours = user.nextInt();

                Project proj = new Project(name, description, hours);
                list.add(proj);
                project = proj;

                System.out.println("Task created.");
                showCurrent();

            } else if (n.equalsIgnoreCase("no") || n.equalsIgnoreCase("n")) {
                System.out.println("Try creating a new task with a different name.");
                add();
            }

        } else {
            System.out.println("What is the description of the task?");
            String description = user.nextLine();
            System.out.println("How many hours does the task allocate?");
            int hours = user.nextInt();

            Project proj = new Project(name, description, hours);
            list.add(proj);
            project = proj;

            System.out.println("Task created.");
            showCurrent();
        }
    }

    public static void delete() {
        if (list.size() == 0) {
            System.out.println("Sorry, there are currently no projects. Please create a project.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (project.getName().equalsIgnoreCase(list.get(i).getName()) && project.getVersion() == (list.get(i).getVersion())) {
                    System.out.println("Deleted task named: " + list.get(i).getName() + list.get(i).getVersion());
                    list.remove(i);
                }
            }
        }
    }

    public static void changeName() {
        System.out.println("What would you like to change the name to?");
        String name = user.next();

        if (name == null) {
            System.out.println("Invalid name.");
        } else {
            String oldName = project.getName();
            version(name);
            project.setName(name);
            System.out.println("Name successfully changed from " + oldName + " to " + name);
        }
    }

    public static void changeDescription() {
        System.out.println("What would you like to change the description to?");
        user.nextLine();
        String description = user.nextLine();

        if (description == null) {
            System.out.println("Invalid description.");
        } else {
            String oldDesc = project.getDescription();
            project.setDescription(description);
            System.out.println("Description successfully changed from " + oldDesc + " to " + description);
        }
    }

    public static void changeHours() {
        System.out.println("What would you like to change the hours to?");
        int hours = user.nextInt();

        if (hours == 0) {
            System.out.println("Invalid amount of hours. Hours can not equal 0.");
        } else {
            int oldHours = project.getHours();
            project.setHours(hours);
            System.out.println("Hours successfully changed from " + oldHours + " to " + hours);
        }
    }

    public static void changeReservation() {
        System.out.println("Input true/false to change the status.");
        boolean reserved = user.nextBoolean();

        if (reserved != project.isReserved()) {
            boolean oldReserved = project.isReserved();
            project.setReserved(reserved);

            System.out.println("Reservation successfully changed from " + oldReserved + " to " + reserved);
        } else {
            System.out.println("Invalid boolean input.");
        }
    }

    public static void quit() {
        loop = false;
        System.out.println("Successfully Quit");
    }

    public static int version(String in) {
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (in.equalsIgnoreCase(list.get(i).getName())) {
                j++;
                list.get(i).setVersion(j);
            }
        }
        return j;

    }
}
