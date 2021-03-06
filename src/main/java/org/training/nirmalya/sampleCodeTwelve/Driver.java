package org.training.nirmalya.sampleCodeTwelve;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;


public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
		
		final Inbox inbox = Inbox.create(system);
				
		ActorRef pongActor = system.actorOf(LifecycleAwareSmartPongActor.props("Buddha"));
       
        inbox.send(pongActor, new PingPongMessageProtocol.PingMessage());
        
        try {
			System.out.println((String)inbox.receive(Duration.create(4, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        system.stop(pongActor);
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
