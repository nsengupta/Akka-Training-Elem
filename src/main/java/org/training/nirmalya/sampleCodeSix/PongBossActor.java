package org.training.nirmalya.sampleCodeSix;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

public class PongBossActor extends UntypedActor {
	
	// TODO [NS]: To be given as exercise
	
	private ActorRef sambhaActor = null;
	private ActorRef kaliaActor =  null;

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		// TODO: Boss doesn't do anything by herself. She asks Kalia and Sambha to do the needful.

	}
	
	public static Props props() {
	    return Props.create(new Creator<PongBossActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public PongBossActor create() throws Exception {
	        return new PongBossActor();
	      }
	    });
	} 

	public PongBossActor() {
	
	}

}
