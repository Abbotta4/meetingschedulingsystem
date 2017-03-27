package meetingschedulingsystem;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class MeetingSchedulingSystem {

    ArrayList<room> rooms = new ArrayList<>();
    
    public void addRoom(room Room) {
        rooms.add(Room);
    }
    
    public void delRoom(room Room) {
        rooms.remove(Room);
    }
    
    public void printSchedule() {
        for(room Room : rooms) {
            System.out.printf("Room %d", args)
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        int temp = -1;
        
        System.out.println("How many rooms are there in the building? (0 to disable max)");
        while (temp < 0) {
            try {
                temp = i.nextInt();
            }catch(RuntimeException e) {
                System.err.println(e);
                i.nextLine();
            }
        }
        room.setMaxRooms(temp);
        
        person james = new person("james", "patterson", 4122902891L);
        person mo = new person("maurice", "meyers", 4122902892L);
        meeting important = new meeting("important", 9, 1);
        
        important.addPerson(james);
        important.addPerson(mo);
        System.out.println("added them successfully");
        important.printPeople();
        
        important.delPerson(james);
        
        important.printPeople();
    }
    
}
