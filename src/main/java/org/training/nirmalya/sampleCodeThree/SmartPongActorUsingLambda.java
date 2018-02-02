package org.training.nirmalya.sampleCodeThree;

import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol.*;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class SmartPongActorUsingLambda extends AbstractActor {
	
	@Override
	public Receive createReceive() {
	    return receiveBuilder()
	        .match(PingMessage.class, m -> {
	        	getSender().tell(new PingPongMessageProtocol.PongMessage(), getSelf());
	        })
	        .matchAny(o ->System.out.println("received unknown message (" + o + ")"))
	        .build();
	  }

}
