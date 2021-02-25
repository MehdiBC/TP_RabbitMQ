package com.application.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendListener implements ActionListener {
    private JTextArea textArea;
    private String queueName;

    public SendListener(JTextArea textArea, String queueName){
        this.textArea=textArea;
        this.queueName=queueName;
    }

    private void send(String queueName){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.queueDeclare(queueName, false, false, false, null);
            String message = textArea.getText();
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [X] Sent '" + message + "'");
        } catch (TimeoutException timeoutException) {
            timeoutException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send(queueName);
    }
}
