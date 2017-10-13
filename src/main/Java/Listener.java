import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("Message at Receiver" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();

        }

    }
}
