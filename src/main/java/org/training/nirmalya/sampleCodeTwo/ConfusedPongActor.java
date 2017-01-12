package org.training.nirmalya.sampleCodeTwo;

import akka.actor.UntypedActor;

public class ConfusedPongActor extends UntypedActor {
	
	

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			getSender().tell("Pong", getSelf());
		}
		else
			unhandled(arg0);

	}
	
	
	public final static class PingMessage { }
	

}
