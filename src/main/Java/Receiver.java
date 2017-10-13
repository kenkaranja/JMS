import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "receive")
public class Receiver extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InitialContext initialContext = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) initialContext.lookup("java:/ConnectionFactory");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();
            QueueSession queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) initialContext.lookup("java:/jms/queue/DLQ");
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);
            queueReceiver.setMessageListener(new Listener());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
