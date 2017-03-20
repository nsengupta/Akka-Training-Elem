package org.training.nirmalya.sampleCodeEleven.protocol;

import java.io.Serializable;

public class PingPongMessageProtocol {
	
	public static class RemotePingMessage implements Serializable { 
	
		private static final long serialVersionUID = 2L;
	}
	
	public static class RemotePongMessage implements Serializable { 
	
		private static final long serialVersionUID = 3L;
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return true;
		}
	}

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
