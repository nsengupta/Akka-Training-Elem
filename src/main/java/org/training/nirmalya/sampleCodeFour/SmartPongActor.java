package org.training.nirmalya.sampleCodeFour;

import org.training.nirmalya.sampleCodeFour.PingPongMessageProtocol.PingMessage;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;


public class SmartPongActor extends UntypedActor {
	
	// TODO: Props is missing; implement.
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof String) { // Be watchful! Which message to consider?
			getSender().tell(this.sayWhat, getSelf());
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	
	
	final private String sayWhat;
	
	public SmartPongActor(final String sayWhat) {
		this.sayWhat = sayWhat;
	}

}
