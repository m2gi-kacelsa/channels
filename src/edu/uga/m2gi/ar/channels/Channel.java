package edu.uga.m2gi.ar.channels;

public class Channel {

	public CircularBuffer readBuffer;
	public CircularBuffer writeBuffer;
	final int CAPACITY_BUFFER = 64;

	public Channel() {
		readBuffer = new CircularBuffer(CAPACITY_BUFFER);
		writeBuffer = new CircularBuffer(CAPACITY_BUFFER);
	}
	
	public Channel(Channel channel) {
		readBuffer = channel.writeBuffer;
		writeBuffer = channel.readBuffer;
	}
	
	public int read(byte[] bytes, int offset, int length) {
		// varaiable to count the number of bytes readen
		int numberBytesReaden = 0;

		synchronized (readBuffer) {
			if (!readBuffer.empty()) {
				if (offset == 0 & length == bytes.length) {
					for (int i = 0; i < bytes.length; i++) {
						bytes[i] = readBuffer.pull();
						numberBytesReaden++;
					}
				} else {
					for (int i = offset; i < length; i++) {
						bytes[i] = readBuffer.pull();
						numberBytesReaden++;
					}
				}

				readBuffer.notify();
			}
			// Buffer Empty
			else {
				System.out.println("readBuffer is Empty!!");
				try {
					readBuffer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return numberBytesReaden;
	}

	public int write(byte[] bytes, int offset, int length) {
		int numberBytesWritten = 0;

		synchronized (writeBuffer) {
			if (writeBuffer.full()) {
				try {
					writeBuffer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (offset == 0 & length == bytes.length) {
					for (int i = 0; i < bytes.length; i++) {
						writeBuffer.push(bytes[i]);
						numberBytesWritten++;
					}
				} else {
					for (int i = offset; i < length; i++) {
						writeBuffer.push(bytes[i]);
						numberBytesWritten++;
					}
				}
				writeBuffer.notify();
			}
		}
		return numberBytesWritten;
	}

	public void disconnect() {
		Broker.rendezVous.clear();
	}

	public boolean disconnected() {
		throw new RuntimeException("Not Implemented Yet");
	}
}
