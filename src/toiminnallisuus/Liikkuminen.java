package toiminnallisuus;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/**
 * Class defines robot's basic movements.
 * 
 * @author antti
 * 
 */
public class Liikkuminen {
    // Declare motors (if that is the right term)
    private EV3LargeRegulatedMotor motorV;
    private EV3LargeRegulatedMotor motorO;

    /**
     * Constructor creates new motor objects and set starting speeds.
     */
    public Liikkuminen() {
        // Create new motor objects and set their initial speeds.
        this.motorV = new EV3LargeRegulatedMotor(MotorPort.B);
        this.motorO = new EV3LargeRegulatedMotor(MotorPort.C);
        this.motorV.setSpeed(600);
        this.motorO.setSpeed(600);
    }

    /**
     * Set default speeds.
     */
    public void setDefault() {
        // Set default speeds.
        this.motorV.setSpeed(600);
        this.motorO.setSpeed(600);
    }

    /**
     * Return current speeds as a String.
     * 
     * @return
     */
    public String getSpeed() {
        // Return current motor speeds as a string.
        String tuloste = "O: " + this.motorO.getSpeed() + " V: " + this.motorV.getSpeed();
        return tuloste;
    }

    /**
     * Increase speed by 100 if current speed below 800. If new speed goes above
     * 800 speed is set to 800.
     */
    public void addSpeed() {
        // If current speed less than 800 increase speed by 100.
        // If New speed over 800 set speed to 800.
        if (this.motorV.getSpeed() < 800) {
            this.motorV.setSpeed(this.motorV.getSpeed() + 100);
            if (this.motorV.getSpeed() > 800) {
                this.motorV.setSpeed(800);
            }
        }
        if (this.motorO.getSpeed() < 800) {
            this.motorO.setSpeed(this.motorO.getSpeed() + 100);
            if (this.motorO.getSpeed() > 800) {
                this.motorO.setSpeed(800);
            }
        }
    }

    /**
     * Decrease speed by 100 if current speed is 100 or more.
     */
    public void decSpeed() {
        if (this.motorV.getSpeed() >= 100) {
            this.motorV.setSpeed(this.motorV.getSpeed() - 100);
        }
        if (this.motorO.getSpeed() >= 100) {
            this.motorO.setSpeed(this.motorO.getSpeed() - 100);
        }
        if(this.motorV.getSpeed() == 0) {
        	this.motorO.setSpeed(0);
        }
    }

    /**
     * Rotates both motors forward.
     */
    public void eteen() {
        this.motorV.forward();
        this.motorO.forward();
    }

    /**
     * Rotates both motors backwards.
     */
    public void taakse() {
        this.motorV.backward();
        this.motorO.backward();
    }

    /**
     * Turn robot left.
     */
    public void vasen() {
        this.motorV.backward();
        this.motorO.forward();
    }

    /**
     * Turn robot right.
     */
    public void oikea() {
        this.motorV.forward();
        this.motorO.backward();
    }

    /**
     * Curve right.
     */
    public void kaarraO() {
        // Curve right by having one motor slower and one faster.
        this.motorO.setSpeed(400);
        this.motorV.setSpeed(800);
        eteen();
        // setDefault();
    }

    /**
     * Curve left.
     */
    public void kaarraV() {
        // Curve left by having one motor slower and one faster.
        this.motorO.setSpeed(800);
        this.motorV.setSpeed(400);
        eteen();
        // setDefault();
    }

    /**
     * Stop motors, back up for 2s and turn 180 degrees to the right.
     */
    public void tormays() {
        // Get current time in ms, go back for 2s and turn 180 degrees.
        long t = System.currentTimeMillis();
        long end = t + 2000;
        while (System.currentTimeMillis() < end) {
            taakse();
        }
        Delay.msDelay(200);
        t = System.currentTimeMillis();
        end = t + 1700;
        while (System.currentTimeMillis() < end) {
            oikea();
        }
    }

    /**
     * Stop both motors.
     */
    public void stop() {
        // Stop both motors with stop(true) method.
        this.motorV.stop(true);
        this.motorO.stop(true);
    }

}


