package com.application.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyJFrame extends JFrame{
    private JPanel pan = new JPanel();
    private JTextArea textArea = new JTextArea();
    private JButton send = new JButton("Send");

    public MyJFrame(String name, String queueName){
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
        //pan.add(send, BorderLayout.SOUTH);
    }
}
