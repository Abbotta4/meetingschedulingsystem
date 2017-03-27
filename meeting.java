package meetingschedulingsystem;

import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class meeting {
    private String name;
    private int time;
    private int duration;
    private ArrayList<person> people;
    
    public meeting(String name, int time, int duration) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.people = new ArrayList<>();
    }
    
    public void addPerson(person newPerson){
        if(null == people || !people.contains(newPerson)) {
            people.add(newPerson);
        }
    }
    
    public void delPerson(person delPerson) {
        people.remove(delPerson);
    }
    
    public void printPeople() {
        for (person Person : people)
            System.out.println(Person.getFirst());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
