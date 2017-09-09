package tech02;

import java.util.Date;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OrderProducer {

	public static void main(String[] args) throws NamingException{
		Float totalAmount=Float.valueOf(args[0]);
		OrderDTO order=new OrderDTO(1234l, new Date(),"John De Vit",totalAmount);
		Context jndiContext=new InitialContext();
        
		ConnectionFactory connectionFactory=(ConnectionFactory)jndiContext.lookup("jms/javaee7/ConnectionFactory");
		Destination topic=(Destination)jndiContext.lookup("jms/javaee7/Topic");
		try(JMSContext jmsContext=connectionFactory.createContext()){
			jmsContext.createProducer().setProperty("orderAmount",totalAmount).send(topic,order);
		}
	}

}
