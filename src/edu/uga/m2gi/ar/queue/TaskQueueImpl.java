package edu.uga.m2gi.ar.queue;

import java.util.ArrayList;

import edu.uga.m2gi.ar.channels.Broker;
import edu.uga.m2gi.ar.channels.RendezVous;

public class TaskQueueImpl extends TaskQueue {

	public String message = "test message";

	public TaskQueueImpl(QueueBroker broker) {
		super(broker);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Broker.rendezVous = new ArrayList<RendezVous>();
		QueueBroker broker1 = new QueueBroker("BrokerA");

		new TaskQueueImpl(broker1) {
			@Override
			public void run() {
				MessageQueue messageQueue = this.m_broker.accept(90);
				try {
					messageQueue.send(message.getBytes(), 0, message.getBytes().length);
					System.out.println("message sent!  . . . . ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		QueueBroker broker2 = new QueueBroker("BrokerB");
		new TaskQueueImpl(broker2) {
			@Override
			public void run() {
				MessageQueue messageQueue = this.m_broker.connect("BrokerA", 90);
				byte[] bytes = new byte[message.getBytes().length];
				try {
					bytes = messageQueue.receive();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
