package org.training.nirmalya.testBed.sampleCodeFive;

import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.actor.Props;


import org.training.nirmalya.testBed.sampleCodeFive.LetterDeliveryProtocol.EnvelopeToDeliver;
import org.training.nirmalya.testBed.sampleCodeFive.LetterDeliveryProtocol.EnvelopeDeliveredConfirmation;


public class PostDelivererActor extends UntypedActor {
	
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof EnvelopeToDeliver) {
			
			EnvelopeToDeliver e = (EnvelopeToDeliver)arg0; 
			getSender()
			.tell(
				new 
				LetterDeliveryProtocol.EnvelopeDeliveredConfirmation(
						e.letter,this.myName
				), 
				getSelf()
			);
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}
	}

	
	public static Props props(String name) {
	    return Props.create(new Creator<PostDelivererActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PostDelivererActor create() throws Exception {
	        return new PostDelivererActor(name);
	      }
	    });
	} 

	
	final String myName;
	
	public PostDelivererActor(final String name) {
		this.myName = name;
	}

}
