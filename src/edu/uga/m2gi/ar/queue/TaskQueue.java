package edu.uga.m2gi.ar.queue;

public abstract class TaskQueue extends Thread {
	QueueBroker m_broker;

	public TaskQueue(QueueBroker broker) {
		super(broker.nom);
		m_broker = broker;
	}

	public abstract void run();

}
