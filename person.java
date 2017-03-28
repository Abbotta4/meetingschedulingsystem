package meetingschedulingsystem;

/**
 * @author ASA5286
 */
public class person {
    private String first;
    private String last;
    private long phone;
    private meeting meetings[];
    
    public person(String first, String last, long phone) {
        this.first = first;
        this.last = last;
        this.phone = phone;
        meetings = new meeting[8];
    }
    
    public person() {
        this("null", "null", 0);
    }
    
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
