package edu.uga.m2gi.ar.channels;

import java.util.ArrayList;

public class TaskImpl extends Task {

	public TaskImpl(Broker broker) {
		super(broker);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Broker.rendezVous = new ArrayList<RendezVous>();
		Broker brokerA = new Broker("BrokerA");
		TaskImpl taskA = new TaskImpl(brokerA);
		taskA.start();
		Broker brokerB = new Broker("BrokerB");
		TaskImpl taskB = new TaskImpl(brokerB);
		taskB.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (super.m_broker.name == "BrokerA") {
			Channel channelA = super.m_broker.accept(45);
		} else {
			Channel channelB = super.m_broker.connect("BrokerA", 45);
		}

	}

}
