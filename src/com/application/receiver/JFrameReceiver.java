package com.application.receiver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JFrameReceiver extends JFrame{
    private final JPanel pan = new JPanel();
    private final JSplitPane sp = new JSplitPane();
    private final JTextArea textArea1 = new JTextArea();
    private final JTextArea textArea2 = new JTextArea();
    private final ArrayList<ThreadReceiver> threadReceivers = new ArrayList<>();

    public JFrameReceiver(String name){
        init(name);
    }

    public void init(String name){
        this.setTitle(name);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.setVisible(true);

        textArea1.setEditable(false);
        textArea2.setEditable(false);

        this.add(pan);
        GridLayout layout = new GridLayout();
        pan.setLayout(layout);
        layout.setHgap(20);
        pan.add(textArea1);
        pan.add(textArea2);
        pan.add(sp);

        sp.setTopComponent(textArea1);
        sp.setBottomComponent(textArea2);
        sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
        sp.setDividerLocation(300);




        threadReceivers.add(new ThreadReceiver("sender1","test1",textArea1));
        threadReceivers.add(new ThreadReceiver("sender2","test2",textArea2));

        for(ThreadReceiver threadReceiver:threadReceivers){
            threadReceiver.start();
        }
    }
}
