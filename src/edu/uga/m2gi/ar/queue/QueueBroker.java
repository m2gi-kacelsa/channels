package edu.uga.m2gi.ar.queue;

import edu.uga.m2gi.ar.channels.Broker;
import edu.uga.m2gi.ar.channels.RendezVous;

public class QueueBroker {
	public String nom;
	private MessageQueue messageQueue;
	private RendezVous rendezVous;
	private Boolean rendezVousExists = false;

	public QueueBroker(String name) {
		nom = name;
	}

	public synchronized MessageQueue accept(int port) {
		for (RendezVous rv : Broker.rendezVous) {
			if (rv.port == port) {
				rendezVous = rv;
				rendezVousExists = true;
				messageQueue = new MessageQueue(rv.getBroker().channel.readBuffer, rv.getBroker().channel.writeBuffer);
			}
		}

		if (!rendezVousExists) {
			rendezVous = new RendezVous(nom, new Broker(nom), port);
			Broker.rendezVous.add(rendezVous);
			messageQueue = new MessageQueue();
		}

		return messageQueue;
	}

	public synchronized MessageQueue connect(String name, int port) {
		for (RendezVous rv : Broker.rendezVous) {
			if (rv.getName().equals(name) && rv.port == port) {
				rendezVous = rv;
				rendezVousExists = true;
				messageQueue = new MessageQueue(rv.getBroker().channel.readBuffer, rv.getBroker().channel.writeBuffer);
			}
		}

		if (!rendezVousExists) {
			rendezVous = new RendezVous(name, new Broker(name), port);
			Broker.rendezVous.add(rendezVous);
			messageQueue = new MessageQueue();
		}

		return messageQueue;

	}

}
