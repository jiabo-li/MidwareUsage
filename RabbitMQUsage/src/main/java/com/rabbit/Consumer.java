package com.rabbit;

import com.rabbitmq.client.*;

public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.61.149.200");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);

        Connection connection;
        Channel channel;

        String exchangeName = "jiabo_exchange";
        String queueName ="jiabo_queque";

        connection = factory.newConnection();
        channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTage,message)->{
          System.out.println("Get message " + new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTage -> {
            System.out.println("Cancel massage");
        };

        channel.basicConsume(queueName,true,deliverCallback,cancelCallback);

        channel.close();
        connection.close();
    }
}
