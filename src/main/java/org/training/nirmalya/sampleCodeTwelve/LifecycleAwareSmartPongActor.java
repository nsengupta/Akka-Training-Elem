package org.training.nirmalya.sampleCodeTwelve;
import org.training.nirmalya.sampleCodeTwelve.PingPongMessageProtocol.PingMessage;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import akka.japi.Option;

public class LifecycleAwareSmartPongActor extends UntypedActor {
	
	@Override
	public void preStart() {
	  System.out.println("LifecycleAwareSmartPongActor.preStart");
	}
	
	@Override
	public void postStop() {
		System.out.println("LifecycleAwareSmartPongActor.postStop");
	  }
	
	public void preRestart(Throwable reason, scala.Option<Object> message) {
		System.out.println("LifecycleAwareSmartPongActor.preRestart");
           
    }

	public void postRestart(java.lang.Throwable reason)
            throws java.lang.Exception {
		System.out.println("LifecycleAwareSmartPongActor.postRestart");
	}

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
	
	public static Props props(final String sayWhat) {
	    return Props.create(new Creator<LifecycleAwareSmartPongActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public LifecycleAwareSmartPongActor create() throws Exception {
	        return new LifecycleAwareSmartPongActor(sayWhat);
	      }
	    });
	} 
	
	String sayWhat = "DontKnow";
	
	public LifecycleAwareSmartPongActor(final String sayWhat) {
		this.sayWhat = sayWhat;
	}


}
