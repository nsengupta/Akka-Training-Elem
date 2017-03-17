package org.training.nirmalya.testBed.sampleCodeFive;


import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;

public class LetterDeliveryTest {
	
    static ActorSystem system;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create("IndianPostSystem");
	}
	  
	@AfterClass
	public static void teardown() {
		JavaTestKit.shutdownActorSystem(system);
		system = null;
	}
	
	@Test
	public void letterDeliveredConfirmationTest() {
		
		final JavaTestKit testDriverEnv = new JavaTestKit(system);
		
		final Props props1 = Props.create(PostDelivererActor.class,"Cheetah");
		final TestActorRef<PostDelivererActor> cheetahActor = 
				TestActorRef.create(system, props1, "DeliveryConfirmationUsingCheetah");
		
		final Props props2 = Props.create(PostDelivererActor.class,"Peregrine-Falcon");
		final TestActorRef<PostDelivererActor> falconActor = 
				TestActorRef.create(system, props2, "DeliveryConfirmationUsingFalcon");
		
		cheetahActor.tell(
				new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tiger"), 
				testDriverEnv.getRef()
			);
		
		falconActor.tell(
				new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tigress"), 
				testDriverEnv.getRef()
			);
		
		LetterDeliveryProtocol.EnvelopeDeliveredConfirmation m1 = 
				testDriverEnv.expectMsgClass(
					new FiniteDuration(1, TimeUnit.SECONDS),
					LetterDeliveryProtocol.EnvelopeDeliveredConfirmation.class
				);
		assertThat(m1.letter, equalTo("Hello, Tiger"));
		assertThat(m1.deliveredBy, equalTo("Cheetah"));
		
		LetterDeliveryProtocol.EnvelopeDeliveredConfirmation m2 = 
				testDriverEnv.expectMsgClass(
					new FiniteDuration(1, TimeUnit.SECONDS),
					LetterDeliveryProtocol.EnvelopeDeliveredConfirmation.class
				);
		assertThat(m2.letter,      equalTo("Hello, Tigress"));
		assertThat(m2.deliveredBy, equalTo("Peregrine-Falcon"));
	}
	
	@Test
	public void letterOutForDeliveryTest() {
		
		final JavaTestKit testDriverEnv = new JavaTestKit(system);
		
		final Props props1 = Props.create(PostMasterActor.class,testDriverEnv.getRef(),testDriverEnv.getRef());
		final TestActorRef<PostMasterActor> postMasterActor = 
				TestActorRef.create(system, props1, "LetterOutForDeliveryIndicationByPostMaster");
		
		
		postMasterActor.tell(
				new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tiger"), 
	            ActorRef.noSender()
			);
		
		postMasterActor.tell(
				new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tigress"), 
				ActorRef.noSender()
			);
		
		Object[] messages = 
				testDriverEnv.expectMsgAllOf(
					new FiniteDuration(1, TimeUnit.SECONDS),
					new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tiger"),
					new LetterDeliveryProtocol.EnvelopeToDeliver("Hello, Tigress")
				);
		
		assertThat(messages[0], instanceOf(LetterDeliveryProtocol.EnvelopeToDeliver.class));
		assertThat(messages[1], instanceOf(LetterDeliveryProtocol.EnvelopeToDeliver.class));
		
		LetterDeliveryProtocol.EnvelopeToDeliver m1 = (LetterDeliveryProtocol.EnvelopeToDeliver) messages[0];
		LetterDeliveryProtocol.EnvelopeToDeliver m2 = (LetterDeliveryProtocol.EnvelopeToDeliver) messages[1];
		
		assertThat(m1.letter,        equalTo("Hello, Tiger"));
		assertThat(m2.letter,        equalTo("Hello, Tigress"));
		
	}

}
