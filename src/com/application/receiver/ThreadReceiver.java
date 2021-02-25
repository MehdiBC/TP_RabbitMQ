package com.application.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;

public class ThreadReceiver extends Thread{
    private String name;
    private String queueName;
    private JTextArea textArea;

    public ThreadReceiver(String name, String queueName, JTextArea textArea){
        this.name=name;
        this.queueName=queueName;
        this.textArea=textArea;
    }

    @Override
    public void run() {
        super.run();
        try{
            receive(queueName);
        }catch (Exception e){e.printStackTrace();}
    }

    public void receive(String queueName) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] Waiting for messages from "+name+". To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery)-> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [X] Received '" + message + "'");
            textArea.setText(message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
