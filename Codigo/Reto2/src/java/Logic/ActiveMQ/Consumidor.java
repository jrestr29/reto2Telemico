package Logic.ActiveMQ;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Consumidor {

    ActiveMQConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;
    JSONArray jsonArr;
    JSONArray jsonMessages;

    public Consumidor() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public JSONArray getMessages(int id, String queue) throws JMSException {

        Destination destination = session.createQueue(queue);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(MyListener);
        
        jsonArr = new JSONArray();
        jsonMessages = new JSONArray();
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("canal", queue);
        jsonObj.put("id", id);
        jsonObj.put("messages", jsonMessages);
        jsonArr.add(jsonObj);
        
        return jsonArr;
    }

    MessageListener MyListener = new MessageListener() {
        JSONArray jsonArr = new JSONArray();

        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage txt = (TextMessage) message;
                String text;

                try {
                    text = txt.getText();
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("msg", text);
                    jsonMessages.add(jsonObj);
                } catch (JMSException ex) {
                    Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
}
