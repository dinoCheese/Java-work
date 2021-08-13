package Session1;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class GoInSquare {
	
	public static void main(String[] args) {
		NXTRegulatedMotor left = Motor.A;
		NXTRegulatedMotor right = Motor.B;
		
		left.setSpeed(250);
		right.setSpeed(250);
		
		for(int i = 0; i < 4; i++) {
			left.forward();
			right.forward();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			
			left.forward();
			right.backward();
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				
			}
			
		}
		
		left.stop();
		right.stop();
	}
		
}
