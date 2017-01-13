package org.training.nirmalya.sampleCodeSeven;



import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;


public class Driver {

	public static void main(String[] args) {
		
		
		Config config = ConfigFactory.load();
		String bossName = 
				config.getConfig("attendeesParams").getString("bossName");  // This works.
		 
		ActorSystem system = ActorSystem.create("Ping-Pong",config);
		
		String bossNameFromAfterActorSystemIsCreated = 
				system.settings().config().getString("attendeesParams.bossName"); // This works too!
		
		// TODO: Print both the names of the boss, obtained and check if they are the same.
		
		
		
		final Inbox inbox = Inbox.create(system);
		
		// TODO: Remove error from the BossActor's creation attempt, below.
        //ActorRef bossActor = system.actorOf(PongBossActor.props(firstPongActor,secondPongActor),"Pong-Gabbar");
        
        // TODO: inbox.send(.....);
        
        try {
			System.out.println((String)inbox.receive(Duration.create(2, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
