package org.training.nirmalya.sampleCodeEleven.remote;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;


public class Driver {

	public static void main(String[] args) {
		
		Config config = ConfigFactory.load("remoting.conf");
		
		ActorSystem system = ActorSystem.create("IAmRemote",config);
		
        ActorRef pongActor = system.actorOf(Props.create(RemotePongActor.class),"Remote");
        
        System.out.println("**Remote pongActor Path=[" + pongActor.path() + "]");
        
        try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
