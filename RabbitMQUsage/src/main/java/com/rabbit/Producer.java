package com.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
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
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,null);

        channel.queueDeclare(queueName,true,false,false,null)
;
        channel.queueBind(queueName,exchangeName,queueName);

        String message = " hello rabbitmq"
;
        channel.basicPublish(exchangeName,queueName,null,message.getBytes());

        channel.close();
        connection.close();


    }
}
