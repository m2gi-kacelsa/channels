package edu.uga.m2gi.ar.channels;

import java.util.List;

public class Broker extends Thread {

	public String name;
	private Channel channel;
	public static List<RendezVous> rendezVous;

	public Broker(String name) {
		super();
		this.name = name;
	}

	public Channel accept(int port) {
		synchronized (rendezVous) {
			rendezVous.add(new RendezVous(name, this, port));
			channel = new Channel();
			System.out.println("rendezVous :: " + rendezVous.size());
		}
		return this.channel;
	}

	public Channel connect(String name, int port) {
		synchronized (rendezVous) {
			rendezVous.add(new RendezVous(name, this, port));
			channel = new Channel();
			System.out.println("rendezVous :: " + rendezVous.size());
		}
		return this.channel;
	}

}
