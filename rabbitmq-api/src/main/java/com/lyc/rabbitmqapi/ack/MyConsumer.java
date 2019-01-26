package com.lyc.rabbitmqapi.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @Auther: Jhon Li
 * @Date: 2019/1/22 12:25
 * @Description:  自定义的消费者
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-------------consume message ---------------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ((Integer) properties.getHeaders().get("num")==0){
          channel.basicNack(envelope.getDeliveryTag(),false,true);
       }else{
           channel.basicAck(envelope.getDeliveryTag(),false);
      }
        System.err.println("body: "+new String(body));


    }
}
