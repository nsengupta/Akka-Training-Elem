package org.training.nirmalya.sampleCodeOne;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorldWithDriver {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("customary-hello-world-java");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell("World!", ActorRef.noSender());
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

	
	private static class Hello extends UntypedActor {
	        
	        public void onReceive(Object message) throws Exception {
	            if (message instanceof String) {
	                System.out.println("Hello, " + message);
	            }
	        }
	 }
}
