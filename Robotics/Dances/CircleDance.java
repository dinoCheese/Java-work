package Task2;

import java.util.Vector;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorConstants;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.PilotProps;

public class CircleDance {

	DifferentialPilot pilot;
	// Parameters: Wheel diameter, track width, left motor, right motor

	// initiate the stuff that you need. Moves, wheel controls
	public Vector<Move> moves;

	public NXTRegulatedMotor left = Motor.A;
	public NXTRegulatedMotor right = Motor.B;
	
	boolean running = true;

	// first constructor
	public CircleDance() {
		moves = new Vector<Move>();
		pilot = new DifferentialPilot(56, 115, Motor.A, Motor.B);
		pilot.setTravelSpeed(100);
		pilot.setRotateSpeed(50);
	}

	// first method
	class Move {
		public int number;
		public Move(int n) {
			number = n;
		}
	}

	// make method for one clap, stop and beep
	public void oneClap() {
		left.stop();
		right.stop();
		Sound.beep();
	}

	// make method for two claps, circle left
	public void twoClap() {
		pilot.rotate(-90);
		pilot.arc(300, 360);
		pilot.rotate(90);
	}

	// make method for three claps, forward & back
	public void threeClap() {

		pilot.travel(100);
		pilot.travel(-100);
	}

	// make method for four claps, wheel around
	public void fourClap() {
		pilot.rotate(360);
	}
	
	/*
	 * Create a thread:
By using the Runnable-Interface
Create a class X which implements Runnable
Again, the run()-Method contains the code for the thread
The thread is started by creating a Thread-Object and using an
instance of X as a parameter
	 */


	public class MovementThread  implements Runnable {
		
		Vector<Move> moves;
		
		public MovementThread(){
			moves = new Vector<Move>();
		}
		
		public void doMove(Move move){
			switch(move.number){
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			default:
				break;
			}
		}
		
		public void run() {
			while (running) {
				if (moves.size() < 1) {
				} else {
					Move move = moves.elementAt(0);
					moves.removeElementAt(0);
					doMove(move);
				}
			}
		}
	}
	
	class ClapsListener implements SensorPortListener{
		
		public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue){
			
		}
	}
		// Main
	public static void main(String[] args) {
		
		Thread ThreadA = new Thread(new MovementThread());
		ThreadA.start();		//moves
		
		SensorPortListener spl = new ClapsListener(); // create instance
		SensorPort.S1.setType(SensorConstants.TYPE_SOUND_DB);
		SensorPort.S1.addSensorPortListener(spl); // register service routine
		Button.ESCAPE.waitForPressAndRelease(); // stop the program


/*
 * Thread A: Controls movements - goes through the queue, excecutes it, if none then ask queue for more
 * Thread B: Listens for sounds - puts these into a queue (vector)
		*/

		
	}
}