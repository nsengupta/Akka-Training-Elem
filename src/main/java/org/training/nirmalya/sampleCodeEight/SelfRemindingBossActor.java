package org.training.nirmalya.sampleCodeEight;


import java.util.concurrent.TimeUnit;

import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SelfRemindingBossActor extends UntypedActor {
	
	private Cancellable cancellable = 
			getContext().system().scheduler().scheduleOnce(
			Duration.create(2, TimeUnit.SECONDS),
            getSelf(),
            new PingPongMessageProtocol.TimeToPingPong(), 
            getContext().system().dispatcher(),
            null);
			

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingPongMessageProtocol.TimeToPingPong) {
			
		   System.out.println("Time To Start a Ping|Pong game!");
			
		}
		
		

	}
	
	public static Props props() {
	    return Props.create(new Creator<SelfRemindingBossActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public SelfRemindingBossActor create() throws Exception {
	        return new SelfRemindingBossActor();
	      }
	    });
	} 

	public SelfRemindingBossActor() {
	
	}

}
