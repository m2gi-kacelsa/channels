package edu.uga.m2gi.ar.queue;

import edu.uga.m2gi.ar.channels.CircularBuffer;

public class MessageQueue {
	public CircularBuffer readBuffer;
	public CircularBuffer writeBuffer;

	public MessageQueue() {
		this.readBuffer = new CircularBuffer(64);
		this.writeBuffer = new CircularBuffer(64);
	}

	public MessageQueue(CircularBuffer readBuffer, CircularBuffer writeBuffer) {
		this.readBuffer = writeBuffer;
		this.writeBuffer = readBuffer;
	}

	public void send(byte[] bytes, int offset, int length) throws InterruptedException {
		synchronized (writeBuffer) {
			while (writeBuffer.full()) {
				writeBuffer.wait();
			}

			for (int i = 0; i < length; i++) {
				writeBuffer.push(bytes[i + offset]);
			}
			writeBuffer.notifyAll();

		}
	}

	public byte[] receive() throws InterruptedException {
		synchronized (readBuffer) {
			while (readBuffer.empty()) {
				readBuffer.wait();
			}
			int i;
			byte[] bytes = new byte[10];
			for (i = 0; i < bytes.length; i++) {
				bytes[i] = readBuffer.pull();
			}
			readBuffer.notifyAll();
			return bytes;

		}
	}

	public void close() {
	}

	public boolean closed() {
		return false;
	}
}
