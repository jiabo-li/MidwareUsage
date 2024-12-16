package com.rabbit.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerFanout {
    public static  void main(String[] args) throws  Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.61.149.200");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);

        Connection connection;
        Channel channel;

        String exchangeName = "jiabo_exchange_fanout";
        String queueName_1 = "queue_name_fanout_1";
        String queueName_2 = "queue_name_fanout_2";
        String queueName_3 = "queue_name_fanout_3";
        String queueName_4 = "queue_name_fanout_4";

        String key_1 = "key_1";
        String key_2 = "key_2";
        String key_3 = "key_3";

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,null);

        channel.queueDeclare(queueName_1,true,false,false,null);
        channel.queueDeclare(queueName_2,true,false,false,null);
        channel.queueDeclare(queueName_4,true,false,false,null);
        channel.queueDeclare(queueName_3,true,false,false,null);

        channel.queueBind(queueName_1,exchangeName,key_1);
        channel.queueBind(queueName_2,exchangeName,key_1);
        channel.queueBind(queueName_3,exchangeName,key_2);
        channel.queueBind(queueName_4,exchangeName,key_3);

        String message = "hello rabbitmq";

        channel.basicPublish(exchangeName,key_1,null,"key1 fanout message".getBytes());
        //channel.basicPublish(exchangeName,key_2,null,"key2".getBytes());
        //channel.basicPublish(exchangeName,key_3,null,"key3".getBytes());

        channel.close();
        connection.close();
    }
}
