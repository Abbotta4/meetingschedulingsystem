package meetingschedulingsystem;

/**
 * @author ASA5286
 */
public class person {
    private String first;
    private String last;
    private int phone;
    
    public person(String first, String last, int phone) {
        this.first = first;
        this.last = last;
        this.phone = phone;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
