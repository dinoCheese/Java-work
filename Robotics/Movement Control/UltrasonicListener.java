package project4;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UltrasonicListener implements Runnable {

	private long time;
	private UltrasonicSensor sensor;
	private LCD screen;
	
	private boolean[][] scannedMap;
	private double[][] chanceMap;
	
	private boolean running;

	public UltrasonicListener() {
		sensor = new UltrasonicSensor(SensorPort.S1);
		screen = new LCD();
		scannedMap = new boolean[64][64];		//64 square grid with 2cm parts
		chanceMap = new double[64][64];			//64 square grid with 2cm parts
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				scannedMap[i][j] = false;			//initially every part is not scanned
				chanceMap[i][j] = 0.5;				//initially the P of every section is half
			}
		}
	}

	@Override
	public void run() {

		while (running) {
			if (System.currentTimeMillis() - time > 100) {
				time = System.currentTimeMillis();
				System.out.println(sensor.getDistance());
			}
		}

		// while (true) {
		// if (System.currentTimeMillis() - time > 100) {
		// System.out.println(sensor.getDistance());
		// time = System.currentTimeMillis();
		// }
		// }

	}
}