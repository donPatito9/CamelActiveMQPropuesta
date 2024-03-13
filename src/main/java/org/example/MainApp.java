package org.example;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.example.router.SimpleRouteBuilder;

import javax.jms.ConnectionFactory;

public class MainApp {

    public static void main(String[] args) throws Exception {
        SimpleRouteBuilder firstRoutBuilder = new SimpleRouteBuilder();
        CamelContext camelContext = new DefaultCamelContext();
        // String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        try {
            camelContext.addRoutes(firstRoutBuilder);
            camelContext.start();
            Thread.sleep(1000 * 60);
            camelContext.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}