package org.training.nirmalya.sampleCodeThree;

import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol.*;

import akka.actor.UntypedActor;

public class SmartPongActor extends UntypedActor {
	
	// TODO: Before modifying code, please refer to the instructions.
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			getSender().tell(new PingPongMessageProtocol.PongMessage(), getSelf());
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}

}
