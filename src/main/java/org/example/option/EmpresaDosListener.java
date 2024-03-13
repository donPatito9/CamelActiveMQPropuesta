package org.example.option;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class EmpresaDosListener {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public EmpresaDosListener() {

    }
    public void receiveMessage() {
        try {
            factory = new ActiveMQConnectionFactory(  ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("foo.bar");
            consumer = session.createConsumer(destination);

            boolean end = false;
            while (!end) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage text = (TextMessage) message;
                    System.out.println("Message Received Successfully : " + text.getText());
                }else {
                    end = true;
                }
            }
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EmpresaDosListener listener2 = new EmpresaDosListener();
        listener2.receiveMessage();
    }
}

