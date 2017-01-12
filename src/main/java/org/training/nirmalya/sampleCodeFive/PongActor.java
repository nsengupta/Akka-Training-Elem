package org.training.nirmalya.sampleCodeFive;



import org.training.nirmalya.sampleCodeFour.PingPongMessageProtocol.PingMessage;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

public class PongActor extends UntypedActor {
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			getSender().tell(this.sayWhat, getSelf());
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}
	}

	public static Props props(final String sayWhat) {
	    return Props.create(new Creator<PongActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PongActor create() throws Exception {
	        return new PongActor(sayWhat);
	      }
	    });
	} 
	
	String sayWhat = "DontKnow";
	
	public PongActor(final String sayWhat) {
		this.sayWhat = sayWhat;
	}
}
