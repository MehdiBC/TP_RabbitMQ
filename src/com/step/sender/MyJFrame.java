package com.step.sender;

import com.step.receiver.ThreadReceiver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyJFrame extends JFrame{
    private JPanel pan = new JPanel();
    private JPanel panSend = new JPanel();
    private JPanel panReceive = new JPanel();
    private JTextArea textAreaForSending = new JTextArea();
    private JTextArea textAreaForReceiving = new JTextArea();
    private JButton send = new JButton("Send");
    static private ArrayList<ThreadReceiver> threadReceivers = new ArrayList<>();

    public MyJFrame(String name, String queueName){
        //setting up the interface
        this.setTitle(name);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        //adding principal pannel with grid layout to the frame
        pan.setLayout(new GridLayout(2,0));
        panSend.setLayout(new BorderLayout());
        pan.add(panSend, 0);
        pan.add(panReceive, 1);
        this.add(pan);
        //configuring the content
        textAreaForSending.setSize(800,300);
        textAreaForSending.setEditable(true);
        textAreaForReceiving.setSize(800,300);
        textAreaForReceiving.setEditable(false);
        //adding an action listener to the send button
        //send.addActionListener(new SendListener(textAreaForSending, queueName));
        //adding the content to the interface
        panSend.add(textAreaForSending, BorderLayout.CENTER);
        panSend.add(send, BorderLayout.SOUTH);
        panReceive.add(textAreaForReceiving);

        threadReceivers.add(new ThreadReceiver(name,queueName,textAreaForReceiving));

        for(ThreadReceiver threadReceiver:threadReceivers){
            threadReceiver.start();
        }
    }
}
