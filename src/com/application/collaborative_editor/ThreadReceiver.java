package com.application.collaborative_editor;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;

public class ThreadReceiver extends Thread{
    private final String name;
    private final String exchange;
    private final JTextArea textArea;


    public ThreadReceiver(String name, String exchange, JTextArea textArea){
        this.name=name;
        this.exchange=exchange;
        this.textArea=textArea;
    }

    @Override
    public void run() {
        super.run();
        try{
            receive(exchange);
        }catch (Exception e){e.printStackTrace();}

    }

    public void receive(String exchange) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchange, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            if(!textArea.getText().equals(message)){
                textArea.setText(message);
            }
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

}

