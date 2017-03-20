package org.training.nirmalya.sampleCodeNine;

import org.training.nirmalya.sampleCodeNine.PingPongMessageProtocol.PingMessage;
import org.training.nirmalya.sampleCodeNine.PingPongMessageProtocol.PongThruValet;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.*;
import akka.japi.Creator;
import scala.concurrent.duration.FiniteDuration;
import akka.japi.Function;
import akka.pattern.Patterns;
import akka.pattern.PipeToSupport;
import akka.pattern.PipeToSupport.PipeableFuture;
import akka.pattern.extended.*;
import akka.util.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import scala.concurrent.*;
import static akka.dispatch.Futures.future;
import static java.util.concurrent.TimeUnit.SECONDS;


public class OutsourcingPongActor extends UntypedActor {
	
	
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof PingMessage) {
			
			System.out.println("Sender: " + getSender());
			
			System.out.println("OutsourcingPongActor: Received Ping, but I will do it myself.");
			
			final CompletableFuture<String> computablePong = 
					CompletableFuture
					.supplyAsync(new Supplier<String>() {
			    @Override
			    public String get() {
			        
			        return "Myself: DoneWithPong";
			    }
			}, getContext().system().dispatcher());
			
			computablePong.thenAccept(s -> getSender().tell(s, getSelf()));
		}
		else
		if (arg0 instanceof PingPongMessageProtocol.PongThruValet) {
			
			Function<Object,String> fn = (r) -> {
	    		String s = (String) r;
	    		return (s);
	    	};
			
			final ActorRef originalSender = getSender();
			
			ActorRef valet = getContext().system().actorOf(LazyPongValetActor.props());
			
			Future<String> valetResponse = 
					
					Patterns.ask(
							valet, 
							(new PingPongMessageProtocol.PingMessage()),
							7000)
							.map(
								new Mapper<Object,String>() {
									public String apply(Object o) {
										return ((String)o);
									}
								}, getContext().system().dispatcher());
			
			akka.pattern.Patterns.pipe(
				valetResponse,
				getContext().system().dispatcher()).to(getSelf());	
		}
		else {
			System.out.println("OutsourcingActor: Received unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	
	public static Props props() {
	    return Props.create(new Creator<OutsourcingPongActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public OutsourcingPongActor create() throws Exception {
	        return new OutsourcingPongActor();
	      }
	    });
	} 

	public OutsourcingPongActor() {
	
	}

}
