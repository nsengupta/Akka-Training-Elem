package org.training.nirmalya.sampleCodeFour;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
public class Driver {

	public static void main(String[] args) {		
		ActorSystem system = ActorSystem.create("Ping-Pong");
		final Inbox inbox = Inbox.create(system);
        ActorRef pongActor = system.actorOf(PongActor.props("Oolala"));
        inbox.send(pongActor, new PingPongMessageProtocol.PingMessage());
        try {
			System.out.println((String)inbox.receive(Duration.create(2, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
		
			e1.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();
	}

}
