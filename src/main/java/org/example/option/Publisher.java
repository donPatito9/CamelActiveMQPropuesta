package org.example.option;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public Publisher() {

    }
    public void sendMessage() {

        try {
            factory = new ActiveMQConnectionFactory( ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("myQueue");
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText("Hello MrRobinson Mensaje Recibido desde Publisher buena Crack");
            producer.send(message);
            System.out.println("Sending Message: " + message.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.sendMessage();
    }

}
