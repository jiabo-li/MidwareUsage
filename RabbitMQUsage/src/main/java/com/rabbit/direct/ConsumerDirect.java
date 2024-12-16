package com.rabbit.direct;

import com.rabbitmq.client.*;

public class ConsumerDirect {
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
        String queueName_1 = "queue_name_1";
        String queueName_2 = "queue_name_2";
        String queueName_3 = "queue_name_3";
        String queueName_4 = "queue_name_4";
        connection = factory.newConnection();
        channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTage, message)->{
            System.out.println("Get message " + new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTage -> {
            System.out.println("Cancel massage");
        };

        channel.basicConsume(queueName_1,true,deliverCallback,cancelCallback);
        channel.basicConsume(queueName_2,true,deliverCallback,cancelCallback);
        channel.basicConsume(queueName_3,true,deliverCallback,cancelCallback);
        channel.basicConsume(queueName_4,true,deliverCallback,cancelCallback);

        channel.close();
        connection.close();
    }
}
