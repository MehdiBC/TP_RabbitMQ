package com.application.sender_receiver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends JFrame{
    private final JPanel pan = new JPanel();
    private final JPanel panSend = new JPanel();
    private final JPanel panReceive = new JPanel();
    private final JTextArea textAreaForSending = new JTextArea();
    private final JTextArea textAreaForReceiving = new JTextArea();
    private final JButton send = new JButton("Send");
    private final String EXCHANGE_NAME = "application";
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
        pan.setLayout(new GridLayout(2, 0));
        panSend.setLayout(new BorderLayout());
        pan.add(panSend, 0);
        pan.add(panReceive, 1);
        this.add(pan);
        //configuring the content
        textAreaForSending.setSize(800, 300);
        textAreaForSending.setEditable(true);
        textAreaForReceiving.setSize(800, 300);
        textAreaForReceiving.setEditable(false);
        //adding an action listener to the send button
        send.addActionListener(new SendListener(textAreaForSending, EXCHANGE_NAME));
        //adding the content to the interface
        panSend.add(textAreaForSending, BorderLayout.CENTER);
        panSend.add(send, BorderLayout.SOUTH);
        panReceive.add(textAreaForReceiving);

       threadReceivers.add(new ThreadReceiver(name,EXCHANGE_NAME,textAreaForReceiving));

    }

    public static void start(){
        for(ThreadReceiver threadReceiver:threadReceivers){
            threadReceiver.start();
        }
    }
}
