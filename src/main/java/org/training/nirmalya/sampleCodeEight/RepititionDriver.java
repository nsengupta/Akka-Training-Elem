package org.training.nirmalya.sampleCodeEight;



import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.training.nirmalya.sampleCodeThree.SmartPongActor;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;


public class RepititionDriver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Ping-Pong");
	
		ActorRef bossActor = system.actorOf(Props.create(RepetitiveSelfRemindingPongBossActor.class));
		
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {/*  ignore  */}
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
