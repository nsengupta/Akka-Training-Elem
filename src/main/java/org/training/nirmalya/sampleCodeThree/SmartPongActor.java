package org.training.nirmalya.sampleCodeThree;

import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol.PingMessage;

import akka.actor.UntypedActor;

public class SmartPongActor extends UntypedActor {
	
	

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			getSender().tell("Pong", getSelf());
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}

}
