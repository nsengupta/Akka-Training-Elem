package org.training.nirmalya.sampleCodeThirteen;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.PoisonPill;
import akka.actor.Props;


public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
		
		final Inbox inbox = Inbox.create(system);
				
		ActorRef pongActor = system.actorOf(LifecycleAwareSmartPongActor.props("Buddha"));
       
        inbox.send(pongActor, new PingPongMessageProtocol.PingMessage("before Poison"));
        
        try {
			System.out.println((String)inbox.receive(Duration.create(4, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        inbox.send(pongActor, PoisonPill.getInstance());
        
        inbox.send(pongActor, new PingPongMessageProtocol.PingMessage("after Poison"));
        
        try {
			System.out.println((String)inbox.receive(Duration.create(4, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
