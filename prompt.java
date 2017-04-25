package meetingschedulingsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * @author ASA5286
 */
public class prompt extends JFrame{
    private final JList<String> options;
    private final JButton confirm, back;

    public prompt(String title, String[] stringList) {
        super(title);

        setLayout(new BorderLayout());

        options = new JList<>(stringList);
        confirm = new JButton("Confirm");
        back = new JButton("Back");
        
        options.setVisibleRowCount(3);
        
        // Only one list selection should be selected at a time
        options.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // set first option to be selected by default
        options.setSelectedIndex(0);
        
        add(options, BorderLayout.CENTER);
        add(confirm, BorderLayout.SOUTH); // lists on the left
        add(back, BorderLayout.SOUTH);

        handler buttonHandler = new handler();
        confirm.addActionListener(buttonHandler);
        back.addActionListener(buttonHandler);
    }
    
    // Listener class for updating the button grid
    private class handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == confirm) {
                switch(event.getActionCommand()) {
                    case "Which room?":
                        break;
                    case "Which meeting?":
                        break;
                    case "Which Person?":
                        break;
                    default:
                }
            }
            
            if(event.getSource() == back) {
                // Display menu
            }
        }
    }
}
