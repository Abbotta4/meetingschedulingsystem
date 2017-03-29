package meetingschedulingsystem;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class MeetingSchedulingSystem {

    private static ArrayList<room> rooms = new ArrayList<>();
    private static ArrayList<person> people = new ArrayList<>();
    
    public static long readLong(String prompt) {
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
    
    public static String readString(String prompt) {
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
    
    public static void printSchedule() {
        for(room Room : rooms) {
            System.out.printf("Room: %d\n", Room.getNumber());
            Room.printMeetings();
        }
        System.out.printf("Participants:\n");
        for(person Person: people) {
            System.out.printf("\t%s %s Phone: %d\n", Person.getFirst(), Person.getLast(), Person.getPhone());
        }
    }
    
    private static boolean ifOption(int sel, ArrayList<int> al) {
        for(int find : al) {
            if(find == sel)
                return true;
        }
        return false;
    }
    
    public static room findRoom(int num) {
        for(room Room : rooms) {
            if(Room.getNumber() == num)
                return Room;
        }
        return null;
    }
    
    public static void addRoom() {
        int num;
        boolean roomExists = false;
        if(0 == room.getMaxRooms() || rooms.size() < room.getMaxRooms()) {
            do {
                num = (int)readLong("What is the room number? ");
            }while(num < 0);
            for(room Room : rooms) {
                if(Room.getNumber() == num)
                    roomExists = true;
            }
            if(!roomExists)
                rooms.add(new room(num));
            else
                System.err.printf("Room %d already exists.\n", num);
        }else {
            System.err.printf("Cannot add room, reached maximum rooms.\n");
        }
    }
    
    public static void delRoom() {
        int num;
        if(!rooms.isEmpty()) {
            do {
                num = (int)readLong("Which room should be deleted? ");
            }while(num < 0);
            room delRoom = findRoom(num);
            if(delRoom != null)
                if(delRoom.isEmpty())
                    rooms.remove(delRoom);
                else
                    System.err.printf("Room %d is non-empty and cannot be deleted.\n", num);
            else
                System.err.printf("Could not find room %d to delete.", num);
        }else {
            System.err.printf("There are no rooms to delete.\n");
        }
    }
    
    public static void addMeeting() {
        boolean success = false;
        String name = readString("What is the name of the meeting? ");
        System.out.println(name);
        int time = (int)readLong("What time will the meeting start? ");
        int duration = (int)readLong("How many hours will the meeting last? ");
        int mRoom = (int)readLong("Which room will the meeting be held in? ");
        for(room Room : rooms) {
            if(Room.getNumber() == mRoom) {
                Room.addMeeting(name, time, duration);
                success = true;
                break;
            }
        }
        if(!success)
            System.err.printf("Could not find room %d\n", mRoom);
    }
    
    public static void delMeeting() {
        int time = (int)readLong("What time does the meeting start? ");
        int mRoom = (int)readLong("Which room is the meeting in? ");
        meeting dMeeting;
        room delRoom = findRoom(mRoom);
        dMeeting = delRoom.findMeeting(time);
        if(dMeeting != null)
            delRoom.delMeeting(dMeeting);
        else
            System.err.printf("Could not find meeting at %d in Room %d.\n", time, mRoom);
    }
     
    public static void addParticipant() {
        String first = readString("What is the first name of the person? ");
        String last = readString("What is the last name of the person? ");
        long phone = readLong("What is the person's phone number? ");
        person personToAdd = new person(first, last, phone);
        for(person Person : people) {
            if(Person.equals(personToAdd)) {
                System.err.printf("%s %s already exists in participants.\n", personToAdd.getFirst(), personToAdd.getLast());
                return;
            }
        }
        people.add(personToAdd);
    }
    
    public static void delParticipant() {
        String first = readString("What is the first name of the person? ");
        String last = readString("What is the last name of the person? ");
        for(person Person : people) {
            if(Person.getFirst().equals(first) && Person.getLast().equalsIgnoreCase(last)) {
                if(Person.noMeetings()) {
                    people.remove(Person);
                    return;
                }else {
                    System.err.printf("%s %s has meetings and cannot be deleted.\n", first, last);
                    return;
                }
            }
        }
        System.err.printf("Could not find %s %s.\n", first, last);
    }
    
    /*
    public static void addToMeeting() {
        String first = readString("What is the first name of the person? ");
        String last = readString("What is the last name of the person? ");
        int time = (int)readLong("What time is this person's meeting? ");
        int mRoom = (int)readLong("In which room is this person's meeting? ");        
        room searchRoom = findRoom(mRoom);
        meeting searchMeeting = searchRoom.findMeeting(time);
        person searchPerson = new person(first, last, 0);
        for(person Person : people) {
            if(Person.equals(searchPerson))
                searchPerson = Person;
        }
        if(searchPerson.getPhone() != 0) {
            searchPerson.addMeeting(searchMeeting);
            searchMeeting.addPerson(searchPerson);                                                
        }else
            System.err.printf("Could not find %s %s in participants\n", first, last);
    }
    */
    
    public static void addToMeeting() {
        for(int i = 0; i < people.size(); i++)
            System.out.printf("%d: %s %s\n", i, people.get(i).getFirst(), people.get(i).getLast());
        int personSel = (int)readLong("Which person should be added to a meeting? ");
        person personToAdd = people.get(personSel);
        
        for(int i = 0; i < rooms.size(); i++)
            System.out.printf("%d: Room %d\n", i, rooms.get(i).getNumber());
        int roomSel = (int)readLong("Which room is the meeting in? ");
        room roomAddedTo = rooms.get(roomSel);
        
        meeting meetings[] = roomAddedTo.getMeetings();
        for(int i = 0; i < meetings.length; i++) {
            if (roomAddedTo.getMeetings()[i] != null && (0 == i || meetings[i] != meetings[i-1])) {
                    int tempTime = meetings[i].getTime() + meetings[i].getDuration();
                    if(tempTime > 12)
                        tempTime -= 12;
                    System.out.printf("%d: %d-%d Meeting: %s\n", i, meetings[i].getTime(), tempTime, meetings[i].getName());
            }
        }
        int meetingSel = (int)readLong("Which meeting should the person be added to? ");
        meeting meetingAddedTo = meetings[meetingSel];
        personToAdd.addMeeting(meetingAddedTo);
        meetingAddedTo.addPerson(personToAdd);
    }
    
    public static void delFromMeeting() {
        String first = readString("What is the first name of the person? ");
        String last = readString("What is the last name of the person? ");
        int time = (int)readLong("At what time is the meeting to be removed from? ");
        int mRoom = (int)readLong("In which room is the meeting that the person is to be removed from? ");
        room dRoom = findRoom(mRoom);
        meeting searchMeeting = dRoom.findMeeting(time);
        if(searchMeeting != null) {
            person searchPerson = searchMeeting.findPerson(first, last);
            if(searchPerson != null) {
                searchPerson.delMeeting(time);
                searchMeeting.delPerson(searchPerson);
            }else
                System.err.printf("Could not find \"%s %s\" in the meeting at %d in room %d.\n", first, last, time, mRoom);
        }else
            System.err.printf("Could not find a meeting at %d in room %d.\n", time, mRoom);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int temp;
        
        do {
            temp = (int)readLong("How many rooms are there in the building? (0 to disable max) ");
        }while (temp < 0);
        room.setMaxRooms(temp);
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
    }
}
