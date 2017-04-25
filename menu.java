package meetingschedulingsystem;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * @author ASA5286
 */
public class menu extends JFrame {
    private JButton select;
    private ButtonGroup buttonGroup;
    private JRadioButton addRoom, delRoom, addMeet, delMeet, addPart, delPart, addPToM, delPFrM;
    
    private static <T> String[] toStringArray(ArrayList<T> list) {
        String[] returnArray = new String[list.size()];
        ArrayList<String> returnList = new ArrayList<>();
        
        for(T item : list)
            returnList.add(item.toString());
        
        returnArray = returnList.toArray(returnArray);
        
        return returnArray;
    }
    
    private static void addRoom() {
        int num;
        String snum;
        boolean roomExists = false;
        do {
            snum = JOptionPane.showInputDialog("Enter room number");
            num = Integer.parseInt(snum);
        }while(num < 0);
        for(room Room : MeetingSchedulingSystem.rooms) {
            if(Room.getNumber() == num)
                roomExists = true;
        }
        if(!roomExists)
            MeetingSchedulingSystem.rooms.add(new room(num));
        else
            System.err.printf("Room %d already exists.\n", num);
    }
    
    public menu() {
        super("Scheduler");
        
        setLayout(new GridLayout(9, 1));
        
        buttonGroup = new ButtonGroup();
        addRoom = new JRadioButton("Add room", true);
        delRoom = new JRadioButton("Delete room", false);
        addMeet = new JRadioButton("Add meeting to room", false);
        delMeet = new JRadioButton("Delete meeting from room", false);
        addPart = new JRadioButton("Add person to participants", true);
        delPart = new JRadioButton("Delete person from participants", false);
        addPToM = new JRadioButton("Add participant to meeting", false);
        delPFrM = new JRadioButton("Delete participant from meeting", false);
        buttonGroup.add(addRoom);
        buttonGroup.add(delRoom);
        buttonGroup.add(addMeet);
        buttonGroup.add(delMeet);
        buttonGroup.add(addPart);
        buttonGroup.add(delPart);
        buttonGroup.add(addPToM);
        buttonGroup.add(delPFrM);
                
        select = new JButton("Show");

        add(addRoom);
        add(delRoom);
        add(addMeet);
        add(delMeet);
        add(addPart);
        add(delPart);
        add(addPToM);
        add(delPFrM);
        add(select);
        
        selectionHandler handler = new selectionHandler();
        select.addActionListener(handler);
    }
    
    private class selectionHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == select) {
                if(addRoom.isSelected())
                    addRoom();
                if(delRoom.isSelected()) {
                    delRoom delRoomP = new delRoom();
                    delRoomP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    delRoomP.setSize(250, 300);
                    delRoomP.setVisible(true);
                    dispose();
                }
                if(addMeet.isSelected())
                    getContentPane().setBackground(new Color(111, 183, 214));
                if(delMeet.isSelected())
                    getContentPane().setBackground(new Color(255, 250, 129));
                if(addPart.isSelected())
                    getContentPane().setBackground(new Color(255, 250, 129));
                if(delPart.isSelected())
                    getContentPane().setBackground(new Color(255, 250, 129));
                if(addPToM.isSelected())
                    getContentPane().setBackground(new Color(255, 250, 129));
                if(delPFrM.isSelected())
                    getContentPane().setBackground(new Color(255, 250, 129));
            }
        }
    }
}
