package org.training.nirmalya.sampleCodeEleven.remote;
import org.training.nirmalya.sampleCodeEleven.protocol.PingPongMessageProtocol;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
public class RemotePongActor extends UntypedActor {
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingPongMessageProtocol.RemotePingMessage) {
			System.out.println("\n");
			System.out.println("Received RemotePingMessage from [" + getSender().path() + "]");
			System.out.println("\n");
			
			getSender()
			.tell(new PingPongMessageProtocol.RemotePongMessage(), getSelf());
			
			System.out.println("\n");
			System.out.println("RemotePongActor: stopping myself!");
			getContext().stop(getSelf());
			
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	public static Props props() {
	    return Props.create(new Creator<RemotePongActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public RemotePongActor create() throws Exception {
	        return new RemotePongActor();
	      }
	    });
	} 

	public RemotePongActor() {
	
	}

}
