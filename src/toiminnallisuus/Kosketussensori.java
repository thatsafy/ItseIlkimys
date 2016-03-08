package toiminnallisuus;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 * 
 * Class that holds the TouchSensors functionality
 * 
 * @author Samu
 *
 */

public class Kosketussensori extends Thread {

	private EV3TouchSensor kosketin;
	private float[] sample;
	
	/**
	 * Initialize the sensor as well as the sample-float
	 */
	public Kosketussensori() {
		this.kosketin = new EV3TouchSensor(SensorPort.S2);
		sample = new float[this.kosketin.sampleSize()];
	}
	
	/**
	 * Method that checks if the sensor is pressed
	 * 
	 * @return boolean
	 */
	public boolean tarkistaOsuma() {		
		this.kosketin.fetchSample(sample, 0);
		if (sample[0] == 1) {
			return true;
		}
		return false;
	}

}

