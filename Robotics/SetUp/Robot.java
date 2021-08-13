package Next;

import java.util.Vector;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorConstants;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot implements Runnable {

	public Vector<Move> moves;  //Defining Vector as a something of Move
	private boolean running = true;     //Defining variable boolean

	DifferentialPilot pilot;

	public NXTRegulatedMotor motorLeft = Motor.A;
	public NXTRegulatedMotor motorRight = Motor.C;

	public Robot() {              //Same as file Name. The constructor and defines moves & pilot
		moves = new Vector<Move>();
		pilot = new DifferentialPilot(56, 115, Motor.A, Motor.C);
	}

	public Vector<Move> getMoves() {
		return moves;
	}  //Getter for Moves

	private void doMove(Move move) {            //The instructions for when moving is invoked

		System.out.println("Running Move: " + move.number);     //states the process

		switch (move.number) {      //list of cases and reactions
		case 1:
			Sound.beep();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
			running = false;
			break;
		case 2:
			pilot.rotate(-90);
			pilot.arc(300, 360);
			pilot.rotate(90);

			break;
		case 3:
			pilot.travel(100);
			pilot.travel(-100);
			break;
		case 4:
			pilot.rotate(360);
			break;
		default:
			break;
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

	}

	public void addMove(Move move) {
		moves.addElement(move);
	}       //Adds moves to the queue

	public void run() {         //Method run
		while (running) {
			if (moves.size() < 1) {

			} else {
				Move move = moves.elementAt(0); //Deals with teh first element in the queueue
				moves.removeElementAt(0);       //gets rid of it
				doMove(move);                   //does the action
			}
		}
	}

	public static void main(String[] args) {
		Robot cd = new Robot();         //new instance of circledance
		SensorPortListener spl = new SoundListener(cd);
		SensorPort.S4.setType(SensorConstants.TYPE_SOUND_DB);
		SensorPort.S4.addSensorPortListener(spl);

		Thread t = new Thread(cd);              //start using the thread
		t.start();
	}
}

class ClapListener implements Runnable {            //second thread class

	public SoundListener sl;                        //initialises variables
	public int claps;

	public ClapListener(SoundListener sl, int n) {              //constructor
		this.sl = sl;
		claps = n;
	}

	public void run() {                     //Listens for Claps
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		if (sl.getClaps() == claps)
			sl.makeClaps(claps);
	}
}

class SoundListener implements SensorPortListener {             //Class for hearing sound

	private Robot cd;                                     //initialises variables
	private long time;
	private volatile int claps;                                 //The fuck is volatile?

	public SoundListener(Robot cd) {
		this.cd = cd;
		time = System.currentTimeMillis();
		claps = 0;
	}

	public int getClaps() {
		return claps;
	}                   //getter

	public void makeClaps(int n) {                            //Method to notice clap and add to queue
		cd.addMove(new Move(n));
		claps = 0;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {            //NOIDEA

		if (System.currentTimeMillis() - time > 100 && aNewValue < 700 && Math.abs(aOldValue - aNewValue) > 100) {
			time = System.currentTimeMillis();                  //the time limits between claps
			System.out.println(aNewValue);
			claps++;                                            //Counts the claps
			Thread t = new Thread(new ClapListener(this, claps));   //creates thread ro count claps
			t.start();                                              //starts the thread
		}
	}
}

class Move {        //understands move

	public int number;

	public Move(int n) {
		number = n;
	}

}