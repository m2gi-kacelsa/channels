package edu.uga.m2gi.ar.channels;

import java.util.List;

import edu.uga.m2gi.ar.queue.QueueBroker;

public class Broker extends Thread {

	public String name;
	public Channel channel;
	public static List<RendezVous> rendezVous;
	boolean alreadyExist = false;

	public Broker(String name) {
		super();
		this.name = name;
	}

	public Channel accept(int port) {
		synchronized (rendezVous) {
			for (RendezVous rv : rendezVous) {
				if (rv.port == port) {
					alreadyExist = true;
					channel = new Channel(rv.getBroker().channel);
				}
			}

			if (!alreadyExist) {
				rendezVous.add(new RendezVous(name, this, port, new QueueBroker(name)));
				channel = new Channel();
			}
		}
		return this.channel;
	}

	public Channel connect(String name, int port) {
		synchronized (rendezVous) {
			for (RendezVous rv : rendezVous) {
				if (rv.getName().equals(name)) {
					alreadyExist = true;
					channel = new Channel(rv.getBroker().channel);
				}
			}

			if (!alreadyExist) {
				rendezVous.add(new RendezVous(name, this, port, new QueueBroker(name)));
				channel = new Channel();
			}
		}
		return this.channel;
	}

}
