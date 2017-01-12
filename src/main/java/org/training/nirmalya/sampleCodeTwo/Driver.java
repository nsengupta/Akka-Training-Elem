package org.training.nirmalya.sampleCodeTwo;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
        ActorRef pongActor = system.actorOf(Props.create(ConfusedPongActor.class));
        
        pongActor
        .tell(
        	  new ConfusedPongActor.PingMessage(), 
        	  ActorRef.noSender()
        	 );
        
        pongActor
        .tell(
        	  new ConfusedPongActor.PingMessage(), 
        	  ActorRef.noSender()
        	 );
        
        pongActor
        .tell(
        	  new ConfusedPongActor.PingMessage(), 
        	  ActorRef.noSender()
        	 );
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {/*  ignore  */}
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
