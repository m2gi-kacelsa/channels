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
		if (this.m_broker.name.equals("BrokerA")) {
			Channel channelA = this.m_broker.accept(10);
			byte[] bytes = {(byte)0x42, (byte)0x12, (byte)0x52, (byte)0x62, (byte)0x82, (byte)0x22};
			System.out.println("write bytes . . .");
			int nb = channelA.write(bytes, 0, bytes.length);
			System.out.println("number written bytes:: "+nb);
		} else {
			Channel channelB = this.m_broker.connect("BrokerA", 10);
			byte[] bytes = new byte[6];
			//read only the first three bytes
			System.out.println("read bytes . . .");
			int nb = channelB.read(bytes, 0, bytes.length);
			System.out.println("nb bytes:: "+nb+ " bytes==>  "+ bytes[0] + " "+ bytes[1]+ " "+ bytes[2]+" "+ bytes[3] + " "+ bytes[4]+ " "+ bytes[5]);
		}

	}

}
