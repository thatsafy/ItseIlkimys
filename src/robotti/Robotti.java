package robotti;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import toiminnallisuus.Katapultti;
import toiminnallisuus.Kosketussensori;
import toiminnallisuus.Liikkuminen;

/**
 *
 * Class where all the robots functions are called through the IR-Sensor
 * 
 * @author Samu
 *
 */

public class Robotti extends Thread {

	//Declare the used objects from other classes
	private EV3IRSensor ir;
	private Liikkuminen liike;
	private Katapultti kata;
	private Kosketussensori kosketin;
	
	//Declare an integer for every channel
	private int remoteCommand1 = 0;
	private int remoteCommand2 = 0;
	private int remoteCommand3 = 0;
	private int remoteCommand4 = 0;

	/**
	 * Constructor for the robot
	 */
	public Robotti() {
		this.ir = new EV3IRSensor(SensorPort.S1);
		this.liike = new Liikkuminen();
		this.kata = new Katapultti();
		this.kosketin = new Kosketussensori();
	}

	/**
	 * The main functionality.
	 * Initializes the robot and displays "Valmis" when the robot is ready to be used
	 */
	public void run() {
		//Write the speed onto the screen
		String speedMotors = liike.getSpeed();
		LCD.drawString(speedMotors, 0, 2);
		
		//Tell the user that the robot is ready
		LCD.drawString("Valmis", 0, 0);
		
		
		while (true) {

			//Set the command-integers to zero
			remoteCommand1 = 0;
			remoteCommand2 = 0;
			remoteCommand3 = 0;
			remoteCommand4 = 0;
			
			//If there's a change in the speed, update the LCD-screen
			if (!liike.getSpeed().equals(speedMotors)) {
				speedMotors = liike.getSpeed();
				LCD.clear(2);
				LCD.drawString(speedMotors, 0, 2);
			}

			//Receive commands from each channel
			remoteCommand1 = ir.getRemoteCommand(0);
			remoteCommand2 = ir.getRemoteCommand(1);
			remoteCommand3 = ir.getRemoteCommand(2);
			remoteCommand4 = ir.getRemoteCommand(3);

			//Receive a sample from the TouchSensor and check if it's pressed
			//If the method returns true, act on that	
			if(kosketin.tarkistaOsuma()) {
				this.liike.tormays();
			}

			//Go through command-integers to see if a button is pressed
			if (remoteCommand1 == 5) {
				break;
			} else if (remoteCommand1 > 0) {
				channelOne();
				Delay.msDelay(200);
			} else if (remoteCommand2 > 0) {
				channelTwo();
				Delay.msDelay(200);
			} else if (remoteCommand3 > 0) {
				channelThree();
				Delay.msDelay(200);
			} else if (remoteCommand4 > 0) {
				channelFour();
			} else {
				liike.stop();
			}
		}
	}
	
	/**
	 * command1: left-top button
	 * command2: left-bottom button
	 * command3: right-top button
	 * command4: right-bottom button
	 * command5: left-top + right-top button
	 */

	/**
	 * Functionality on the first channel
	 */
	private void channelOne() {
		if (remoteCommand1 == 1) {
			liike.eteen();
		} else if (remoteCommand1 == 2) {
			liike.taakse();
		} else if (remoteCommand1 == 3) {
			liike.vasen();
		} else if (remoteCommand1 == 4) {
			liike.oikea();
		}
	}

	/**
	 * Functionality on the second channel
	 */
	private void channelTwo() {
		if (remoteCommand2 == 1) {
			liike.kaarraV();
		} else if (remoteCommand2 == 2) {
			liike.kaarraO();
		} else if (remoteCommand2 == 3) {
			Sound.buzz();
			kata.shoot();
		} else if (remoteCommand2 == 4) {
			kata.shoot2();
		}
	}

	/**
	 * Functionality on the third channel
	 */
	private void channelThree() {
		if (remoteCommand3 == 1) {
			kata.addSpeed();
			Delay.msDelay(50);
			kata.updateScreen();
		} else if (remoteCommand3 == 2) {
			kata.decSpeed();
			Delay.msDelay(50);
			kata.updateScreen();
		} else if (remoteCommand3 == 3) {
			kata.addAngle();
			Delay.msDelay(50);
			kata.updateScreen();
		} else if (remoteCommand3 == 4) {
			kata.decAngle();
			Delay.msDelay(50);
			kata.updateScreen();
		}
	}
	
	/**
	 * Functionality on the fourth channel
	 */
	private void channelFour() {
		if (remoteCommand4 == 1) {
			liike.addSpeed();
			Delay.msDelay(100);
		} else if (remoteCommand4 == 2) {
			liike.decSpeed();
			Delay.msDelay(100);
		}
	}
}
