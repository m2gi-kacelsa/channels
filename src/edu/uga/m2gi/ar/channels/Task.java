package edu.uga.m2gi.ar.channels;

public abstract class Task extends Thread {
	Broker m_broker;
	public Task(Broker broker) {
		super(broker.getName());
		m_broker = broker;
	}
	public abstract void run();
}
