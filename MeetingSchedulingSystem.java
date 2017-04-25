package meetingschedulingsystem;

import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * @author ASA5286
 */
public class MeetingSchedulingSystem {

    public static ArrayList<room> rooms = new ArrayList<>();
    public static ArrayList<person> people = new ArrayList<>();
    
    private static long readLong(String prompt) {
        long temp;
        Scanner i = new Scanner(System.in);
        System.out.printf(prompt);
        while(true) {
            try {
                temp = i.nextLong();
                return temp;
            }catch(RuntimeException e){
                System.err.println(e);
                i.nextLine();
                System.out.printf(prompt);
            }
        }
    }
    
    private static String readString(String prompt) {
        String temp;
        Scanner i = new Scanner(System.in);
        System.out.printf(prompt);
        while(true) {
            try {
                temp = i.nextLine();
                return temp;
            }catch(RuntimeException e){
                System.err.println(e);
                i.nextLine();
                System.out.printf(prompt);
            }
        }
    }
    
    private static void printSchedule() {
        for(room Room : rooms) {
            System.out.printf("Room: %d\n", Room.getNumber());
            Room.printMeetings();
        }
        System.out.printf("Participants:\n");
        for(person Person: people) {
            System.out.printf("\t%s %s Phone: %d\n", Person.getFirst(), Person.getLast(), Person.getPhone());
        }
    }
    
    public static void delRoom(int roomSel) {
        if(rooms.isEmpty()) {
            System.err.println("No rooms to delete.");
            return;
        }
        room delRoom = rooms.get(roomSel);
        if(delRoom.isEmpty())
            rooms.remove(delRoom);
        else{
            System.err.printf("Room %d is non-empty and cannot be deleted.\n", roomSel);
        }
    }
    
    public static void addMeeting(String name, int room, int time, int duration) {
        if(rooms.isEmpty()) {
            System.err.println("No rooms to add meetings to");
            return;
        }        
        room addRoom = rooms.get(room);
        addRoom.addMeeting(name, time, duration);
    }
    
    private static void delMeeting() {
        if(rooms.isEmpty()) {
            System.err.println("No rooms to delete meetings from.");
            return;
        }
        
        int roomSel;
        do {
            for(int i = 0; i < rooms.size(); i++)
                System.out.printf("%d: Room %d\n", i, rooms.get(i).getNumber());
            roomSel = (int)readLong("Which room should the meeting be deleted from? ");
        }while(roomSel < 0 || roomSel > rooms.size() - 1);
        room delRoom = rooms.get(roomSel);
        
        meeting meetings[] = delRoom.getMeetings();
        int meetingSel;
        do {
            for(int i = 0; i < meetings.length; i++) {
                if (delRoom.getMeetings()[i] != null && (0 == i || meetings[i] != meetings[i-1])) {
                        int tempTime = meetings[i].getTime() + meetings[i].getDuration();
                        if(tempTime > 12)
                            tempTime -= 12;
                        System.out.printf("%d: %d-%d Meeting: %s\n", i, meetings[i].getTime(), tempTime, meetings[i].getName());
                }
            }
            meetingSel = (int)readLong("Which meeting should be deleted? ");            
            if(meetings[meetingSel] != null && meetings[meetingSel].getTime() != meetingSel + 9)
                meetingSel = -1;
        }while(meetingSel < 0 || meetingSel > meetings.length - 1 || meetings[meetingSel] == null);
        delRoom.delMeeting(meetings[meetingSel]);
    }
     
    private static void addParticipant() {
        String first = readString("What is the first name of the person? ");
        String last = readString("What is the last name of the person? ");
        long phone;
        do {
            phone = readLong("What is the person's phone number? ");
            if(phone < 1000000000L || phone > 9999999999L)
                System.err.println("Invalid phone number, please try again.");
        }while(phone < 1000000000L || phone > 9999999999L);
        person personToAdd = new person(first, last, phone);
        for(person Person : people) {
            if(Person.equals(personToAdd)) {
                System.err.printf("%s %s already exists in participants.\n", personToAdd.getFirst(), personToAdd.getLast());
                return;
            }
        }
        people.add(personToAdd);
    }
    
    private static void delParticipant() {
        if(people.isEmpty()) {
            System.err.println("No people to delete.");
            return;
        }
        
        int personSel;    
        do {
            for(int i = 0; i < people.size(); i++)
                System.out.printf("%d: %s %s\n", i, people.get(i).getFirst(), people.get(i).getLast());
            personSel = (int)readLong("Which person should be deleted? ");
        }while(personSel < 0 || personSel > people.size() - 1);
        person personToDel = people.get(personSel);
        people.remove(personToDel);
    }
    
    private static void addToMeeting() {
        if(people.isEmpty()) {
            System.err.println("No people to add to meetings.");
            return;
        }
        
        int personSel;    
        do {
            for(int i = 0; i < people.size(); i++)
                System.out.printf("%d: %s %s\n", i, people.get(i).getFirst(), people.get(i).getLast());
            personSel = (int)readLong("Which person should be added to a meeting? ");
        }while(personSel < 0 || personSel > people.size() - 1);
        person personToAdd = people.get(personSel);

        if(rooms.isEmpty()) {
            System.err.println("No rooms to add person to.");
            return;
        }
        
        int roomSel;
        do {
            for(int i = 0; i < rooms.size(); i++)
                System.out.printf("%d: Room %d\n", i, rooms.get(i).getNumber());
            roomSel = (int)readLong("Which room is the meeting in? ");
        }while(roomSel < 0 || roomSel > rooms.size() - 1);
        room roomAddedTo = rooms.get(roomSel);
        
        if(roomAddedTo.isEmpty()) {
            System.err.printf("No meetings in room %d\n", roomAddedTo.getNumber());
            return;
        }
            
        meeting meetings[] = roomAddedTo.getMeetings();
        int meetingSel;
        do {
            for(int i = 0; i < meetings.length; i++) {
                if (roomAddedTo.getMeetings()[i] != null && (0 == i || meetings[i] != meetings[i-1])) {
                        int tempTime = meetings[i].getTime() + meetings[i].getDuration();
                        if(tempTime > 12)
                            tempTime -= 12;
                        System.out.printf("%d: %d-%d Meeting: %s\n", i, meetings[i].getTime(), tempTime, meetings[i].getName());
                }
            }
            meetingSel = (int)readLong("Which meeting should the person be added to? ");            
        }while(meetingSel < 0 || meetingSel > meetings.length - 1 || meetings[meetingSel] == null);
        meeting meetingAddedTo = meetings[meetingSel];
        personToAdd.addMeeting(meetingAddedTo);
        meetingAddedTo.addPerson(personToAdd);
    }
    
    private static void delFromMeeting() {
        
        if(rooms.isEmpty()) {
            System.err.println("No rooms to delete people from.");
            return;
        }
        
        int roomSel;
        do {
            for(int i = 0; i < rooms.size(); i++)
                System.out.printf("%d: Room %d\n", i, rooms.get(i).getNumber());
            roomSel = (int)readLong("Which room is the meeting in? ");
        }while(roomSel < 0 || roomSel > rooms.size() - 1);
        room roomDelFrom = rooms.get(roomSel);
        
        if(roomDelFrom.isEmpty()) {
            System.err.printf("No meetings in room %d\n", roomDelFrom.getNumber());
            return;
        }
            
        meeting meetings[] = roomDelFrom.getMeetings();
        int meetingSel;
        do {
            for(int i = 0; i < meetings.length; i++) {
                if (roomDelFrom.getMeetings()[i] != null && (0 == i || meetings[i] != meetings[i-1])) {
                        int tempTime = meetings[i].getTime() + meetings[i].getDuration();
                        if(tempTime > 12)
                            tempTime -= 12;
                        System.out.printf("%d: %d-%d Meeting: %s\n", i, meetings[i].getTime(), tempTime, meetings[i].getName());
                }
            }
            meetingSel = (int)readLong("Which meeting is the person to be removed in? ");            
        }while(meetingSel < 0 || meetingSel > meetings.length - 1 || meetings[meetingSel] == null);
        meeting meetingDelFrom = meetings[meetingSel];
        
        ArrayList<person> meetingPeople = meetingDelFrom.getPeople();
        if(meetingPeople.isEmpty()) {
            System.err.println("No people to remove from meeting.");
            return;
        }
        
        int personSel;    
        do {
            for(int i = 0; i < meetingPeople.size(); i++)
                System.out.printf("%d: %s %s\n", i, meetingPeople.get(i).getFirst(), meetingPeople.get(i).getLast());
            personSel = (int)readLong("Which person should be removed from the meeting? ");
        }while(personSel < 0 || personSel > meetingPeople.size() - 1);
        person personToDel = meetingPeople.get(personSel);
        
        personToDel.delMeeting(meetingDelFrom.getTime());
        meetingDelFrom.delPerson(personToDel);
    }
    
    public static void mainMenu() {
        menu main = new menu();
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(250, 300);
        main.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int temp;
        
        mainMenu();

        /*
        do {           
            temp = (int)readLong("\n1: Add room\n2: Delete room\n3: Add meeting to room\n4: Delete meeting from room\n"
                + "5: Add person to participants.\n6: Delete person from participants.\n7: Add participant to meeting\n8: Delete participant from meeting\n"
                    + "9: Print schedule\n10: Quit\n\nOption (1, 2, 3 ...): ");
            System.out.printf("\n");
            switch(temp){
                case 1: // Add room
                    addRoom();
                    break;
                case 2: // Delete room
                    delRoom();
                    break;
                case 3: // Add meeting to room
                    addMeeting();
                    break;
                case 4: // Delete meeting from room
                    delMeeting();
                    break;
                case 5: // Add person to participants
                    addParticipant();
                    break;
                case 6: // Delete person from participants
                    delParticipant();
                    break;
                case 7: // Add participant to meeting
                    addToMeeting();
                    break;
                case 8: // Delete participant from meeting
                    delFromMeeting();
                    break;
                case 9: // Print schedule
                    printSchedule();
                    break;
                case 10: // Quit
                    break;
                default:
                    System.err.println("Invalid menu selection.");
            }
        }while(temp != 10);
        */
    }
}
