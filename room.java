package meetingschedulingsystem;

/**
 * @author ASA5286
 */
public class room {
    private static int maxRooms = 0;
    private int number;
    private meeting[] meetings;

    public room(int number) {
        this.number = number;
        meetings = new meeting[8];
    }
    
    public static int getMaxRooms() {
        return maxRooms;
    }

    public static void setMaxRooms(int aMaxRooms) {
        maxRooms = aMaxRooms;
    }
    
    public meeting findMeeting(String name) {
        for(meeting Meeting : meetings) {
            if(Meeting.getName().equals(name))
                return Meeting;
        }
        return null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addMeeting(meeting newMeeting) {
        for(int i = newMeeting.getTime() - 9; i < newMeeting.getDuration(); i++)
            meetings[i] = newMeeting;
    }
    
    public void delMeeting(meeting delMeeting) {
        
    }
    
    public void printMeetings() {
            for (int i = 0; i < meetings.length; i++) {
                if (meetings[i] != null && (0 == i || meetings[i] != meetings[i-1])) {
                    int tempTime = meetings[i].getTime() + meetings[i].getDuration();
                    if(tempTime > 12)
                        tempTime -= 12;
                    System.out.printf("\t%d-%d Meeting: %s\n", meetings[i].getTime(), tempTime, meetings[i].getName());
                    meetings[i].printPeople();
                }
            }
    }
    
    public boolean isEmpty() {
        for (meeting Meeting : meetings) {
            if (Meeting != null)
                return false;
        }
        return true;
    }
}
