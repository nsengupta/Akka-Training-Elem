package org.training.nirmalya.sampleCodeFive;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

public class PongBossActor extends UntypedActor {
	
	// TODO [NS]: To be given as exercise
	
	private ActorRef pingFlunkey1 = null;
	private ActorRef pingFlunkey2 = null;

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		// TODO: Make Sambha and Kalia identify themselves, 
		// when they respond to Pong messages

	}
	
	public static Props props(final ActorRef actor1, final ActorRef actor2) {
	    return Props.create(new Creator<PongBossActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PongBossActor create() throws Exception {
	        return new PongBossActor(actor1,actor2);
	      }
	    });
	} 

	public PongBossActor(final ActorRef actor1, final ActorRef actor2) {
		this.pingFlunkey1 = actor1;
		this.pingFlunkey2 = actor2;
	}

}
