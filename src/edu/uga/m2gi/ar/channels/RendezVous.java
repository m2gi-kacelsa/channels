package edu.uga.m2gi.ar.channels;

import edu.uga.m2gi.ar.queue.QueueBroker;

public class RendezVous {

	private String name;
	private Broker broker;
	public final int port;
	private QueueBroker queueBroker;

	public RendezVous(String name, Broker broker, int port, QueueBroker queueBroker) {
		super();
		this.name = name;
		this.broker = broker;
		this.port = port;
		this.queueBroker = queueBroker;
	}

	public QueueBroker getQueueBroker() {
		return queueBroker;
	}

	public void setQueueBroker(QueueBroker queueBroker) {
		this.queueBroker = queueBroker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

}
