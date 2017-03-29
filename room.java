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
    
    public meeting[] getMeetings() {
        return meetings;
    }
    
    public meeting findMeeting(long time) {
        for(int i = 0; i < meetings.length; i++) {
            if(meetings[i] != null && meetings[i].getTime() == time)
                return meetings[i];
        }
        return null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addMeeting(String name, int time, int duration) {
        boolean fit = true;
        if(time >= 1 && time <= 5)
            time += 12;
        if(time >= 9 && time <= 17 && time + duration <=17) {
            for(int i = time - 9; i < duration; i++) {
                if(meetings[i] != null)
                    fit = false;
            }
            if(fit) {
            meeting temp = new meeting(name, time, duration);
            for(int i = time - 9; i < duration; i++)
                meetings[i] = temp;
            }else {
                System.err.printf("Meeting conflicts with another meeting.\n");
            }
        }else {
            System.err.printf("Meetings must begin and end on the hour between 9 and 5.\n");
        }
    }
    
    public void delMeeting(meeting delMeeting) {
        for(int i = delMeeting.getTime() - 9; i < delMeeting.getDuration(); i++)
            meetings[i] = null;
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
