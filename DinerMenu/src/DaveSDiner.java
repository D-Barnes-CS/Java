/**
 * 
 */
/**
 * @author D
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DaveSDiner implements ActionListener{
    JLabel jlabD, jlabF, jlabS, jlabM;
    
    DaveSDiner() {
        JFrame jfrm = new JFrame("Dave's Diner");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(600, 350);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jlabD = new JLabel("Drinks: ");
        
        JRadioButton b1 = new JRadioButton("Small");
        b1.addActionListener(this);
        jfrm.add(b1);
        
        JRadioButton b2 = new JRadioButton("Medium");
        b2.addActionListener(this);
        jfrm.add(b2);
        
        JRadioButton b3 = new JRadioButton("Large");
        b3.addActionListener(this);
        jfrm.add(b3);
        
        ButtonGroup drinks = new ButtonGroup();
        drinks.add(b1); drinks.add(b2); drinks.add(b3);
        jlabS = new JLabel("Select one drink please");
        jfrm.add(jlabS);
        jfrm.setVisible(true);
        
        jlabF = new JLabel("Dinner: ");
        
        JRadioButton b4 = new JRadioButton("Cheeseburger Meal");
        b4.addActionListener(this);
        jfrm.add(b4);
        
        JRadioButton b5 = new JRadioButton("Fish Dinner");
        b5.addActionListener(this);
        jfrm.add(b5);
        
        JRadioButton b6 = new JRadioButton("Chicken Sandwich and Chips");
        b6.addActionListener(this);
        jfrm.add(b6);
        
        ButtonGroup meals = new ButtonGroup();
        meals.add(b4); meals.add(b5); meals.add(b6);
        jlabM = new JLabel("Select one meal please");
        jfrm.add(jlabM);
        jfrm.setVisible(true);
        
        
        String[] sizes = {"Small", "Medium", "Large"};
        JRadioButton[][] drink = {{b1}, {b2}, {b3}};
        JTable table = new JTable(drink, sizes);
        JScrollPane scrl = new JScrollPane(table);
        table.setFillsViewportHeight(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        jlabD.setText("You selected " + ae.getActionCommand() + " and ");
        jlabM.setText(ae.getActionCommand());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DaveSDiner();
            }
        });
    }
    
}
