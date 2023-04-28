import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

public class BeatBox {
    private ArrayList<JCheckBox> chckbxLst;
    private Sequencer sqncr;
    private Sequence sqnc;
    private Track trk;
    
    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", 
        "Acoustinc Snare", "Crash Cymbal", "Hand Clap", "High Tom", "HI Bongo",
        "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Lowmid Tom",
        "HIgh Agogo", "OPen Hi COnga"};
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 
        67, 63};
    
    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }
    
    public void buildGUI() {
        JFrame jfrm = new JFrame("Cyber BeatBox");
        jfrm.setDefaultCloseOperation(jfrm.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Box btnBox = new Box(BoxLayout.Y_AXIS);
        JButton strt = new JButton("Start");
        strt.addActionListener(e -> buildTrackAndStart());
        btnBox.add(strt);
        
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> sqncr.stop());
        btnBox.add(stop);
        
        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(e -> changeTempo(1.03f));
        btnBox.add(upTempo);
        
        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(e -> changeTempo(0.97f));
        btnBox.add(downTempo);
        
        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (String instrumentName : instrumentNames) {
            JLabel instrumentLabel = new JLabel(instrumentName);
            instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            nameBox.add(instrumentLabel);
        }
    
    background.add(BorderLayout.EAST, btnBox);
    background.add(BorderLayout.WEST, nameBox);
    
    jfrm.getContentPane().add(background);
    
    GridLayout grid = new GridLayout(16, 16);
    grid.setVgap(1); grid.setHgap(2);
    
    JPanel mainPanel = new JPanel(grid);
    background.add(BorderLayout.CENTER, mainPanel);
    
    chckbxLst = new ArrayList<>();
    for(int i = 0; i < 256; i++) {
        JCheckBox c = new JCheckBox();
        c.setSelected(false);
        chckbxLst.add(c);
        mainPanel.add(c);
    }
    
    setUpMidi();
    jfrm.setBounds(50, 50, 300, 300);
    jfrm.pack();
    jfrm.setVisible(true);
    }
    
    private void setUpMidi() {
        try {
            sqncr = MidiSystem.getSequencer();
            sqncr.open();
            sqnc = new Sequence(Sequence.PPQ, 4);
            trk = sqnc.createTrack();
            sqncr.setTempoInBPM(120);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void buildTrackAndStart() {
        int[] trckLst;
        
        sqnc.deleteTrack(trk);
        trk = sqnc.createTrack();
        
        for (int i = 0; i < 16; i++) {
            trckLst = new int[16];
            int key = instruments[i];
            for (int j = 0; j < 16; j++){
                JCheckBox jc = chckbxLst.get(j + 25 * i);
                if (jc.isSelected()) {
                    trckLst[j] = key;
                }
                else {
                    trckLst[j] = 0;
                }
            }
            makeTracks(trckLst);
            trk.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 16));
        }
        trk.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 15));
        try {
            sqncr.setSequence(sqnc);
            sqncr.setLoopCount(sqncr.LOOP_CONTINUOUSLY);
            sqncr.setTempoInBPM(120);
            sqncr.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
}
    
    private void changeTempo(float tempoMultiplier) {
        float tempoFactor = sqncr.getTempoFactor();
        sqncr.setTempoFactor(tempoFactor * tempoMultiplier);
    }
    
    private void makeTracks(int[] list) {
        for(int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                trk.add(makeEvent(NOTE_ON, 9, key, 100, i));
                trk.add(makeEvent(NOTE_OFF,9,key,100,i+1));
            }
        }
    }
    public static MidiEvent makeEvent(int cmd, int chn1, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(cmd, chn1, one, two);
            event = new MidiEvent(msg, tick);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return event;
    }}
    
    
