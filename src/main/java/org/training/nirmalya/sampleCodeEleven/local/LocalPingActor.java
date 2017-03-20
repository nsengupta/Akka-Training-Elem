package org.training.nirmalya.sampleCodeEleven.local;



import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorPath;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import org.training.nirmalya.sampleCodeEleven.protocol.PingPongMessageProtocol;

public class LocalPingActor extends UntypedActor {
	
	ActorSelection remoteGuy = 
			getContext()
			.actorSelection("akka.tcp://IAmRemote@127.0.0.1:6101/user/Remote");
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingPongMessageProtocol.PingMessage) {
			System.out.println("\n");
			System.out.println("Sending RemotePingMessage to [" + remoteGuy.pathString() + "]");
			remoteGuy.tell(new PingPongMessageProtocol.RemotePingMessage(), getSelf());
		}
		else
		if (arg0 instanceof PingPongMessageProtocol.RemotePongMessage) {
			System.out.println("\n");
			System.out.println("Received RemotePongMessage from [" + getSender().path());
			System.out.println("\n");
			
			System.out.println("Local PingActor: Killing myself");
			getSelf().tell(PoisonPill.getInstance(), ActorRef.noSender());
			
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	
	public static Props props() {
	    return Props.create(new Creator<LocalPingActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public LocalPingActor create() throws Exception {
	        return new LocalPingActor();
	      }
	    });
	} 

	public LocalPingActor() {
	
	}

}
