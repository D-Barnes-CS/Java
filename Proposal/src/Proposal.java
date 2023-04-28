
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Proposal implements ActionListener {
    JLabel prop; int countY = 0, countN = 0;
    //creating buttons and java frames
    Proposal() {
        JFrame jfrm = new JFrame("Prop 138");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(300, 100);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton propNo = new JButton("YES");
        propNo.addActionListener(this);
        jfrm.add(propNo);
        
        JButton propYes = new JButton("NO");
        propYes.addActionListener(this);
        jfrm.add(propYes);
        
        prop = new JLabel("SELECT OUR YOUTH'S FUTURE!");
        jfrm.add(prop);
        jfrm.setVisible(true);
    }
    //if yes is clicked, add that to the counter and display it in terminal
    // if no is clicked, add to the counter for no
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand() == "YES") {
            countY++;
            prop.setText("You voted to save the building!! YES VOTES: " + countY);
            System.out.println(countY);
        }
        else {
            countN++;
            prop.setText("You voted to save money!! NO VOTES: " + countN);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Proposal();
            }
        });
    }
    
}
