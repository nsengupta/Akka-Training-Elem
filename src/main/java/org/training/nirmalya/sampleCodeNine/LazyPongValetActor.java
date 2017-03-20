package org.training.nirmalya.sampleCodeNine;

import org.training.nirmalya.sampleCodeNine.PingPongMessageProtocol.PingMessage;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import scala.concurrent.duration.Duration;
import akka.japi.Function;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import scala.concurrent.*;
import static akka.dispatch.Futures.future;
import static java.util.concurrent.TimeUnit.SECONDS;

public class LazyPongValetActor extends UntypedActor {
	
	// TODO: Before modifying code, please refer to the instructions.
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			
			System.out.println("Valet: Received Ping, but I will take time to do the job!");
			
			try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {/*  ignore  */}
			
			getSender().tell("Valet: DoneWithPong", getSelf());
		}
		else {
			System.out.println("Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	
	public static Props props() {
	    return Props.create(new Creator<LazyPongValetActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public LazyPongValetActor create() throws Exception {
	        return new LazyPongValetActor();
	      }
	    });
	} 

	public LazyPongValetActor() {
	
	}

}
