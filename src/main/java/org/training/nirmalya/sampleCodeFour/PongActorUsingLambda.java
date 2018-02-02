package org.training.nirmalya.sampleCodeFour;



import org.training.nirmalya.sampleCodeFour.PingPongMessageProtocol.PingMessage;
import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.AbstractActor.Receive;
import akka.japi.Creator;

public class PongActorUsingLambda extends AbstractActor {
	
	@Override
	public Receive createReceive() {
	    return receiveBuilder()
	        .match(PingMessage.class, m -> {
	        	getSender().tell(this.sayWhat, getSelf());
	        })
	        .matchAny(o ->System.out.println("received unknown message (" + o + ")"))
	        .build();
	  }

	public static Props props(final String sayWhat) {
	    return Props.create(new Creator<PongActorUsingLambda>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PongActorUsingLambda create() throws Exception {
	        return new PongActorUsingLambda(sayWhat);
	      }
	    });
	} 
	
	private String sayWhat = "DontKnow";
	
	public PongActorUsingLambda(final String sayWhat) {
		this.sayWhat = sayWhat;
	}
}
