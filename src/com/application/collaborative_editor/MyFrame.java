package com.application.collaborative_editor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame{
    private final JPanel pan = new JPanel();
    private final JTextArea textArea = new JTextArea();
    private final JButton send = new JButton("Send");
    static private final ArrayList<ThreadReceiver> threadReceivers = new ArrayList<>();

    public MyFrame(String name){
        init(name);
    }
    public void init(String name) {
        //setting up the interface
        this.setTitle(name);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        //adding principal pannel with grid layout to the frame
        pan.setLayout(new BorderLayout());
        this.add(pan);
        //configuring the content
        textArea.setSize(800, 300);
        textArea.setEditable(true);
        //adding an action listener to the send button
        String EXCHANGE_NAME = "application";
        send.addActionListener(new SendListener(textArea, EXCHANGE_NAME));
        //adding the content to the interface
        pan.add(textArea, BorderLayout.CENTER);
        pan.add(send, BorderLayout.SOUTH);

       threadReceivers.add(new ThreadReceiver(name, EXCHANGE_NAME,textArea));

    }

    public static void start(){
        for(ThreadReceiver threadReceiver:threadReceivers){
            threadReceiver.start();
        }
    }
}
