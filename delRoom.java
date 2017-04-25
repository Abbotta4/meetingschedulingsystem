package meetingschedulingsystem;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;

/**
 * @author ASA5286
 */
public class delRoom extends JFrame{
    private final JList<Integer> options;
    private final JButton confirm, back;

    public delRoom() {
        super("Which room should be deleted?");

        int numRooms = MeetingSchedulingSystem.rooms.size();
        Integer[] roomNumbers = new Integer[numRooms];
        for(int i = 0; i < numRooms; i++)
            roomNumbers[i] = MeetingSchedulingSystem.rooms.get(i).getNumber();

        setLayout(new BorderLayout());

       // Integer[] roomNumbers = {1, 2, 3, 4};
        
        options = new JList<>(roomNumbers);
        confirm = new JButton("Confirm");
        back = new JButton("Back");
        
        options.setVisibleRowCount(3);
        
        // Only one list selection should be selected at a time
        options.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // set first option to be selected by default
        options.setSelectedIndex(0);
        
        add(options, BorderLayout.CENTER);
        //add(back, BorderLayout.SOUTH);
        add(confirm, BorderLayout.SOUTH);

        handler buttonHandler = new handler();
        confirm.addActionListener(buttonHandler);
        back.addActionListener(buttonHandler);
    }
    
    // Listener class for updating the button grid
    private class handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int roomSel = options.getSelectedIndex();
            if(event.getSource() == confirm) {
                MeetingSchedulingSystem.delRoom(roomSel);
                MeetingSchedulingSystem.mainMenu();
                dispose();
            } else{
                System.err.printf("Room %d is non-empty and cannot be deleted.\n", roomSel);
        }
            if(event.getSource() == back) {
                // Display menu
            }
        }
    }
}
