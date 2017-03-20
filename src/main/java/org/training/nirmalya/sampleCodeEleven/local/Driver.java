package org.training.nirmalya.sampleCodeEleven.local;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.training.nirmalya.sampleCodeEleven.protocol.PingPongMessageProtocol;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;


public class Driver {

	public static void main(String[] args) {
		
		Config config = ConfigFactory.load("local.conf");
		
		ActorSystem system = ActorSystem.create("I-Am-Local",config);
		
        ActorRef pingActor = system.actorOf(Props.create(LocalPingActor.class),"Local");
        
        System.out.println("Local pingActor [" + pingActor.path() + "]");
        
        Inbox.create(system).send(pingActor, new PingPongMessageProtocol.PingMessage());
        
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
