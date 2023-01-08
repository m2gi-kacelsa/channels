package edu.uga.m2gi.ar.queue;

import edu.uga.m2gi.ar.channels.Broker;
import edu.uga.m2gi.ar.channels.RendezVous;

public class QueueBroker {
	public String nom;
	private MessageQueue messageQueue;
	private Boolean rendezVousExists = false;

	public QueueBroker(String name) {
		nom = name;
	}

	public synchronized MessageQueue accept(int port) {
		for (RendezVous rv : Broker.rendezVous) {
			if (rv.port == port) {
				rendezVousExists = true;
				messageQueue = new MessageQueue(rv.getQueueBroker().messageQueue);
			}
		}

		if (!rendezVousExists) {
			Broker broker = new Broker(nom);
			broker.accept(port);
			messageQueue = new MessageQueue();
		}

		return messageQueue;
	}

	public synchronized MessageQueue connect(String name, int port) {
		for (RendezVous rv : Broker.rendezVous) {
			if (rv.getName().equals(name) && rv.port == port) {
				rendezVousExists = true;
				messageQueue = new MessageQueue(rv.getQueueBroker().messageQueue);
			}
		}

		if (!rendezVousExists) {
			Broker broker = new Broker(nom);
			broker.accept(port);
			messageQueue = new MessageQueue();
		}

		return messageQueue;

	}

}
