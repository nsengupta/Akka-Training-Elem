package org.training.nirmalya.testBed.sampleCodeThree;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;

import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol.PongMessage;
import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol.PingMessage;
import org.training.nirmalya.sampleCodeThree.PingPongMessageProtocol;
import org.training.nirmalya.sampleCodeThree.SmartPongActor;

import scala.concurrent.duration.FiniteDuration;

public class SmartPongActorTest {
	
static ActorSystem system;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create("SmartPongActorTestSystem");
	}
	  
	@AfterClass
	public static void teardown() {
		JavaTestKit.shutdownActorSystem(system);
		system = null;
	}
	
	@Test
	public void pongMessageTest() {
			
		final JavaTestKit testDriverEnv = new JavaTestKit(system);
		final Props props = Props.create(SmartPongActor.class);
		final TestActorRef<SmartPongActor> smartPongActorRef = TestActorRef.create(system, props, "pongMessageTest");
		
		smartPongActorRef.tell(new PingPongMessageProtocol.PingMessage(), testDriverEnv.getRef());		
		PongMessage m = testDriverEnv.expectMsgClass(
				new FiniteDuration(1, TimeUnit.SECONDS),
				PingPongMessageProtocol.PongMessage.class);
		assertEquals(m.toString(),"PongMessage");
		
	}
	
	@Test
	public void unknownMessageTest() {
		new JavaTestKit(system) {{
			
			final Props props = Props.create(SmartPongActor.class);
			final TestActorRef<SmartPongActor> smartPongActorRef = TestActorRef.create(system, props, "unknownMessageTest");
			final SmartPongActor actor = smartPongActorRef.underlyingActor();
			
			smartPongActorRef.tell("Abracadabra", getRef());		
			expectNoMsg(duration("2 second"));
		}};
	}


}
