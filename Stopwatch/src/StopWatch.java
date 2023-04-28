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
import java.util.*;

class StopWatch implements ActionListener {
    JLabel jlab;
    long start;
    long restart;

	StopWatch() {
	    JFrame jfrm = new JFrame("A Simple Stopwatch");
	    jfrm.setLayout(new FlowLayout());
	    jfrm.setSize(350, 120);
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton jbtnStart = new JButton("Start");
	    JButton jbtnStop = new JButton("Stop");
	    JButton jbtnStatus = new JButton("Status");
	    JButton jbtnRestart = new JButton("Restart");
	    jbtnStart.addActionListener(this);
	    jbtnStop.addActionListener(this);
	    jbtnStatus.addActionListener(this);
	    jbtnRestart.addActionListener(this);
	    jfrm.add(jbtnStart);
	    jfrm.add(jbtnStop);
	    jfrm.add(jbtnStatus);
	    jfrm.add(jbtnRestart);
	    jlab = new JLabel ("Press Start to begin timing.");
	    jfrm.add(jlab);
	    jfrm.setVisible(true);
	    }
	    public void actionPerformed(ActionEvent ae) {
	        Calendar cal = Calendar.getInstance();
	
	        if (ae.getActionCommand().equals("Start")) {
	            start = cal.getTimeInMillis();
	            jlab.setText("Stopwatch is Running..."); }
	        if (ae.getActionCommand().equals("Status")) {
	            jlab.setText("STATUS UPDATE: Elapsed time is " + 
	                    (double)(cal.getTimeInMillis() - start) / 1000); }
	        if (ae.getActionCommand().equals("Restart")) {
	            cal.setTimeInMillis(0);
	            start = cal.getTimeInMillis();
	            jlab.setText("Stopwatch has Restarted..."); }
	        else {
	        jlab.setText("Elapsed time is " + 
	                (double)(cal.getTimeInMillis() - start) / 1000);
	        }}
	
	
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new StopWatch();
	            }
	    });
	    }
}