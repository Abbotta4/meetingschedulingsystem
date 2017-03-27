package meetingschedulingsystem;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class MeetingSchedulingSystem {

    private static ArrayList<room> rooms = new ArrayList<>();
    
    public static long readLong(Scanner i, String prompt) {
        long temp;
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
    
    public static String readString(Scanner i, String prompt) {
        String temp;
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
        do {
            temp = -1;
            System.out.printf("1: Add room\n2: Add meeting to room\n3: Add person to meeting\n4: Quit\nOption (1, 2, 3, 4): ");
            do {
                try {
                    temp = i.nextInt();
                }catch(RuntimeException e){
                    System.err.println(e);
                    i.nextLine();
                    System.out.printf("Option (1, 2, 3, 4): ");
                }
                
                switch(temp){
                    case 1:
                        int num = -1;
                        if(rooms.size() < room.getMaxRooms()) {
                            do {
                                num = (int)readLong(i, "What is the room number? ");
                            }while(num < 0);
                            rooms.add(new room(num));
                        } else {
                            System.err.printf("Cannot add room, reached maximum rooms.\n");
                        }
                        break;
                    case 2:
                        boolean addMeetingSuccess = false;
                        String name = readString(i, "What is the name of the meeting? ");
                        int time = (int)readLong(i, "What time will the meeting start? ");
                        int duration = (int)readLong(i, "How many hours will the meeting last? ");
                        int mroom = (int)readLong(i, "Which room will the meeting be held in? ");
                        for(room Room : rooms) {
                            if(Room.getNumber() == mroom) {
                                Room.addMeeting(new meeting(name, time, duration));
                                addMeetingSuccess = true;
                                break;
                            }
                        }
                        if(!addMeetingSuccess)
                            System.err.printf("Could not find room %d\n", mroom);
                        break;
                    case 3:
                        boolean addPersonSuccess = false;
                        String firstName = readString(i, "What is the first name of the person? ");
                        String lastName = readString(i, "What is the last name of the person? ");
                        long phone = readLong(i, "What is the person's phone number? ");
                        String mname = readString(i, "Which meeting should this person be added to? ");
                        for(room Room : rooms) {
                            meeting searchMeeting = Room.findMeeting(mname);
                            if(searchMeeting != null) {
                                searchMeeting.addPerson(new person(firstName, lastName, phone));
                                addPersonSuccess = true;
                            }    
                        }
                        if(!addPersonSuccess)
                            System.err.printf("Could not find meeting \"%s\"\n", mname);
                        break;
                    case 4:
                        break;
                }
                
            }while(temp < 1 && temp > 4);
        }while(temp != 4);
        
        person james = new person("james", "patterson", 4122902891L);
        person mo = new person("maurice", "meyers", 4122902892L);
        meeting important = new meeting("important", 9, 1);
        room entry = new room(201);
        rooms.add(entry);
        entry.addMeeting(important);
        important.addPerson(james);
        important.addPerson(mo);
     
        printSchedule();
    }
}
