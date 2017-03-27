package meetingschedulingsystem;

/**
 * @author ASA5286
 */
public class room {
    private static int maxRooms = 0;
    private int number;
    private meeting[] meetings;

    public static int getMaxRooms() {
        return maxRooms;
    }

    public static void setMaxRooms(int aMaxRooms) {
        maxRooms = aMaxRooms;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addMeeting(meeting newMeeting) {
        
    }
    
    public void delMeeting(meeting delMeeting) {
        
    }
    
    public void printMeetings() {
        
    }
}
