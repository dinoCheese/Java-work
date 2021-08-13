package project4;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class MovementControl implements Runnable {

	public DifferentialPilot pilot;

	public MovementControl() {
		pilot = new DifferentialPilot(56, 125, Motor.A, Motor.C);
	}

	@Override
	public void run() {
//		while (true) {
//			pilot.setRotateSpeed(45);
//			pilot.rotate(360);
//		}
		
		pilot.travel(1280);	//full length
		pilot.rotate(180); 	//turn at end
		pilot.travel(1280);	//go to start
		pilot.rotate(180);	//turn around
		pilot.travel(640);	//go to middle
		pilot.rotate(90);	//turn left
		pilot.travel(640);	//left edge
		pilot.rotate(180);	//turn around
		pilot.travel(1280); //right end
		pilot.rotate(180);	//turn around
		pilot.travel(640);	//center
		
		}

}