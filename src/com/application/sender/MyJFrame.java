package com.application.sender;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame{
    private final JPanel pan = new JPanel();
    private final JTextArea textArea = new JTextArea();
    private final JButton send = new JButton("Send");

    public MyJFrame(String name, String queueName){
        init(name, queueName);
    }

    public void init(String name, String queueName){
        //setting up the interface
        this.setTitle(name);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        //adding a pannel with border layout
        this.add(pan);
        pan.setLayout(new BorderLayout());
        //configuring the content
        textArea.setSize(800,500);
        textArea.setEditable(true);
        //adding an action listener to the send button
        send.addActionListener(new SendListener(textArea, queueName));
        //adding the content to the interface
        pan.add(textArea, BorderLayout.CENTER);
        pan.add(send, BorderLayout.SOUTH);
    }
}
