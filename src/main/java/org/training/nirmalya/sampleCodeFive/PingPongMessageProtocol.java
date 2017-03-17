package org.training.nirmalya.sampleCodeFive;

import java.io.Serializable;

public class PingPongMessageProtocol {
	
	public static class PingMessage implements Serializable { 
		private static final long serialVersionUID = 1L;
		public String toString() {
			return ("PingMessage");
		}
	}
	
	public static class PongMessage { 
		private static final long serialVersionUID = 1L;
		public String toString() {
			return ("PongMessage");
		}
	}
	

}
