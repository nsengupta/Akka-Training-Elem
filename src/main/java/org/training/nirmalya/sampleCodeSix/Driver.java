package org.training.nirmalya.sampleCodeSix;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;


public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
		
		ActorRef bossActor = system.actorOf(
				PongBossActor.props(),"Pong-Gabbar");
		
		final Inbox inbox = Inbox.create(system);
		
		inbox.send(bossActor, new PingPongMessageProtocol.PingMessage());
        
        try {
			System.out.println((String)inbox.receive(Duration.create(2, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        inbox.send(bossActor, new PingPongMessageProtocol.PongMessage());
        
        try {
			System.out.println((String)inbox.receive(Duration.create(2, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
