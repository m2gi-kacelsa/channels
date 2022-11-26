package edu.uga.m2gi.ar.channels;

public class RendezVous {

	private String name;
	private Broker broker;
	public final int port;

	public RendezVous(String name, Broker broker, int port) {
		super();
		this.name = name;
		this.broker = broker;
		this.port = port;
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
