import java.util.ArrayList;

public class Project {

    private String name;
    private String description;
    private int hours;
    private int version;
    private String deadline;
    private boolean reserved;


    public Project (String name, String description, int hours, String deadline) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.deadline = deadline;
        reserved = false;
        version = 0;

    }

    //Getters
    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public int getHours() {
        return hours;
    }
    public boolean isReserved() {
        return reserved;
    }
    public int getVersion() {return version;}
    public String getDeadline() {
        return deadline;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    public void setVersion(int version) { this.version = version;}
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int compareTo(Project in) {
        if (name.compareTo(in.getName()) > 0) {
            return 1;
        } else if (name.compareTo(in.getName()) < 0) {
            return -1;
        } else {
            if (version > in.getVersion()) {
                return 1;
            } else if (version < in.getVersion()) {
                return -1;
            } else {
                if (deadline.compareTo(in.getDeadline()) > 0) {
                    return 1;
                } else if (deadline.compareTo(in.getDeadline()) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
