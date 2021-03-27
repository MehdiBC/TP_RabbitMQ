package com.application.collaborative_editor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendListener implements ActionListener {
    private final JTextArea textArea;
    private final String exchange;

    public SendListener(JTextArea textArea, String exchange){
        this.textArea=textArea;
        this.exchange = exchange;
    }

    private void send(String exchange){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchange, "fanout");
            String message = textArea.getText();
            channel.basicPublish(exchange, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send(exchange);
    }
}

