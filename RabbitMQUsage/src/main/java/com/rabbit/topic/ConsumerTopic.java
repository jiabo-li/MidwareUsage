package com.rabbit.topic;

import com.rabbitmq.client.*;

public class ConsumerTopic {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.61.149.200");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);

        Connection connection;
        Channel channel;

        String exchangeName = "jiabo_exchange_topic";
        String queueName ="jiabo_queque";
        String queueName_1 = "queue_name_topic_1";
        String queueName_2 = "queue_name_topic_2";
        String queueName_3 = "queue_name_topic_3";
        String queueName_4 = "queue_name_topic_4";
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,null);


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
