package org.training.nirmalya.sampleCodeNine;



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
				
        ActorRef pongActor = system.actorOf(Props.create(OutsourcingPongActor.class));
        
        inbox.send(pongActor, new PingPongMessageProtocol.PongThruValet());
        inbox.send(pongActor, new PingPongMessageProtocol.PingMessage());
        inbox.send(pongActor, new PingPongMessageProtocol.PongThruValet());
        
        try {
			/*System.out.println((String)*/inbox.receive(Duration.create(1, TimeUnit.SECONDS));
			/*System.out.println((String)*/inbox.receive(Duration.create(5, TimeUnit.SECONDS));
			/*System.out.println((String)*/inbox.receive(Duration.create(5, TimeUnit.SECONDS));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
