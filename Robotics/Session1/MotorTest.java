package Session1;

import lejos.nxt.Button;
import lejos.nxt.Motor;

public class MotorTest {
	
	public static void main(String[] args) {
		Motor.A.setSpeed(1000);
		Motor.B.setSpeed(1000);
		Motor.A.forward();
		Motor.B.forward();
		
		Button.ENTER.waitForPress();
	}

}
