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

	String sayWhat = "DontKnow";
	
	public PongActor(final String sayWhat) {
		this.sayWhat = sayWhat;
	}
}
