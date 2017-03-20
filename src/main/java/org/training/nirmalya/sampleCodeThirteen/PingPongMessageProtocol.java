package org.training.nirmalya.sampleCodeThirteen;

import java.io.Serializable;

public class PingPongMessageProtocol {
	
	public static class PingMessage implements Serializable { 
	
		public final String when;
		public PingMessage(String when) {
			this.when = when;
		}
		public String toString() {
			return ("PingMessage");
		}
	}
	
	
	public static class PongMessage { 
		public String toString() {
			return ("PongMessage");
		}
	}
	

}
