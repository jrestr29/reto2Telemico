package Logic.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Productor {

    ActiveMQConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;

    public Productor() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }


    public JSONArray produceMsg(String name, String mensaje) throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        destination = session.createQueue(name);
        TextMessage message = session.createTextMessage(mensaje);
        producer.send(message);
        
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", true);

        return jsonArr;
    }
    
}
