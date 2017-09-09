package tech02;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(mappedName = "jms/javaee7/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "orderAmount > 1000"),
        @ActivationConfigProperty(
                propertyName = "destinationType", 
                propertyValue = "javax.jms.Topic"),
@ActivationConfigProperty(
                propertyName = "destination", 
                propertyValue = "jms_javaee7_Topic")
})
@JMSConnectionFactoryDefinition(name = "jms/javaee7/ConnectionFactory",
        className = "javax.jms.ConnectionFactory")
@JMSDestinationDefinition(name = "jms/javaee7/Topic",
        className = "javax.jms.Topic", interfaceName = "javax.jms.Topic")
public class ExpenciveOrderMDB implements MessageListener{

	public void onMessage(Message message) {
		try {
		      OrderDTO order = message.getBody(OrderDTO.class);
		      System.out.println("Expensive order received: " + order.toString());
		    } catch (JMSException e) {
		      e.printStackTrace();
		    }
		
	}

}

