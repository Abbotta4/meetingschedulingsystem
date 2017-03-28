package meetingschedulingsystem;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class MeetingSchedulingSystem {

    private static ArrayList<room> rooms = new ArrayList<>();
    
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
    }
    
    public static room findRoom(int rnum) {
        for(room Room : rooms) {
            if(Room.getNumber() == rnum)
                return Room;
        }
        return null;
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
            do {
                temp = (int)readLong("\n1: Add room\n2: Delete room\n3: Add meeting to room\n4: Delete meeting from room\n"
                    + "5: Add person to meeting\n6: Delete person from meeting\n7: Print schedule\n8: Quit\n\nOption (1, 2, 3 ...): ");
                System.out.printf("\n");
                switch(temp){
                    case 1: // Add room
                        int numAdd;
                        boolean roomExists = false;
                        if(0 == room.getMaxRooms() || rooms.size() < room.getMaxRooms()) {
                            do {
                                numAdd = (int)readLong("What is the room number? ");
                            }while(numAdd < 0);
                            for(room Room : rooms) {
                                if(Room.getNumber() == numAdd)
                                    roomExists = true;
                            }
                            if(!roomExists)
                                rooms.add(new room(numAdd));
                            else
                                System.err.printf("Room %d already exists.\n", numAdd);
                        }else {
                            System.err.printf("Cannot add room, reached maximum rooms.\n");
                        }
                        break;
                    case 2: // Delete room
                        int numDel = -1;
                        if(rooms.isEmpty()) {
                            do {
                                numDel = (int)readLong("Which room should be deleted? ");
                            }while(numDel < 0);
                            room delRoom = findRoom(numDel);
                            if(delRoom != null)
                                rooms.remove(delRoom);
                            else
                                System.err.printf("Could not find room %d to delete.", numDel);
                        }else {
                            System.err.printf("There are no rooms to delete.\n");
                        }
                        break;
                    case 3: // Add meeting to room
                        boolean addMeetingSuccess = false;
                        String mAddName = readString("What is the name of the meeting? ");
                        System.out.println(mAddName);
                        int time = (int)readLong("What time will the meeting start? ");
                        int duration = (int)readLong("How many hours will the meeting last? ");
                        int mAddRoom = (int)readLong("Which room will the meeting be held in? ");
                        for(room Room : rooms) {
                            if(Room.getNumber() == mAddRoom) {
                                Room.addMeeting(mAddName, time, duration);
                                addMeetingSuccess = true;
                                break;
                            }
                        }
                        if(!addMeetingSuccess)
                            System.err.printf("Could not find room %d\n", mAddRoom);
                        break;
                    case 4: // Delete meeting from room
                        int mDelTime = (int)readLong("What time does the meeting start? ");
                        int mDelRoom = (int)readLong("Which room is the meeting in? ");
                        meeting dMeeting;
                        room delRoom = findRoom(mDelRoom);
                        dMeeting = delRoom.findMeeting(mDelTime);
                        if(dMeeting != null)
                            delRoom.delMeeting(dMeeting);
                        else
                            System.err.printf("Could not find meeting at %d in Room %d.\n", mDelTime, mDelRoom);
                        break;
                    case 5: // Add person to meeting
                        String addFirstName = readString("What is the first name of the person? ");
                        String addLastName = readString("What is the last name of the person? ");
                        long phone = readLong("What is the person's phone number? ");
                        int mAddedToTime = (int)readLong("What time is this person's meeting? ");
                        int mAddedToRoom = (int)readLong("In which room is this person's meeting? ");
                        room rAddedTo = findRoom(mAddedToRoom);
                        meeting searchMeeting = rAddedTo.findMeeting(mAddedToTime);
                        if(searchMeeting != null)
                            searchMeeting.addPerson(new person(addFirstName, addLastName, phone));
                        else
                            System.err.printf("Could not find a meeting at %d in room %d,\n", mAddedToTime, mAddedToRoom);
                        break;
                    case 6: // Delete person from meeting
                        String delFirstName = readString("What is the first name of the person? ");
                        String delLastName = readString("What is the last name of the person? ");
                        int mDelFromTime = (int)readLong("At what time is the meeting to be removed from? ");
                        int mDelFromRoom = (int)readLong("In which room is the meeting that the person is to be removed from? ");
                        room rDelFrom = findRoom(mDelFromRoom);
                        meeting searchPersonsMeeting = rDelFrom.findMeeting(mDelFromTime);
                        if(searchPersonsMeeting != null) {
                            person searchPerson = searchPersonsMeeting.findPerson(delFirstName, delLastName);
                            if(searchPerson != null)
                                searchPersonsMeeting.delPerson(searchPerson);
                            else
                                System.err.printf("Could not find \"%s%s\" in the meeting at %d in room %d.\n", delFirstName, delLastName, mDelFromTime, mDelFromRoom);
                        }else
                            System.err.printf("Could not find a meeting at %d in room %d.\n", mDelFromTime, mDelFromRoom);
                        break;
                    case 7: // Print schedule
                        printSchedule();
                        break;
                    case 8: // Quit
                        break;
                }
                
            }while(temp < 1 && temp > 8);
        }while(temp != 8);
    }
}
