package org.training.nirmalya.sampleCodeSeven;

import java.io.Serializable;

public class PingPongMessageProtocol {
	
	public static class PingMessage implements Serializable { 
		private static final long serialVersionUID = 1L;
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
