package org.training.nirmalya.testBed.sampleCodeFive;


import org.training.nirmalya.testBed.sampleCodeFive.LetterDeliveryProtocol.*;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.actor.Props;


public class PostMasterActor extends UntypedActor {
	

	private final  ActorRef postFlier ;
	private final  ActorRef postRunner;
	private        int whoseTurn = 0;

	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof EnvelopeToDeliver) {
			
			if (whoseTurn == 0) {
				
				this.postFlier.tell(arg0, getSelf());
				whoseTurn = 1;
			}
			else {
				
				this.postRunner.tell(arg0, getSelf());
				whoseTurn = 0;
			}
		}
		else
		if (arg0 instanceof EnvelopeDeliveredConfirmation)	{
			
			EnvelopeDeliveredConfirmation m = (EnvelopeDeliveredConfirmation) arg0;
			
			System.out.println(
					"PostMaster received confirmation of delivery:Letter " 
			        + "[" + m.letter + "]"
			        + ",by [" + m.deliveredBy + "]");
			
		}
		else {
			System.out.println("Unknown Message");
			unhandled(arg0);
		}
		

	}
	
	public static Props props(final ActorRef postPerson1, final ActorRef postPerson2) {
	    return Props.create(new Creator<PostMasterActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PostMasterActor create() throws Exception {
	        return new PostMasterActor(postPerson1, postPerson2);
	      }
	    });
	} 

	public PostMasterActor(final ActorRef postPerson1, final ActorRef postPerson2) {
	
		this.postFlier = postPerson1;
		this.postRunner = postPerson2;
	}


}
