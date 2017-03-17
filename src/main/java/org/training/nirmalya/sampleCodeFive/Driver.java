package org.training.nirmalya.sampleCodeFive;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;


public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
		
		/*final Inbox inbox = Inbox.create(system);
		
		ActorRef firstPongActor  = system.actorOf(
				PongActor.props("Hello"), 
				"Kalia");
		ActorRef secondPongActor = system.actorOf(
				PongActor.props("Hello"), 
				"Sambha");
				
        ActorRef bossActor = system.actorOf(
        		PongBossActor.props(
        				firstPongActor,
        				secondPongActor),
        				"Gabbar");*/
        
        // Just a template, to help you remember how to use an Inbox!
        //
        // inbox.send(pongActor, new PingPongMessageProtocol.PingMessage());
        //
        // Now, prepare to receive through the inbox.
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
