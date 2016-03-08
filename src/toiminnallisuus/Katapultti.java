package toiminnallisuus;

import lejos.hardware.lcd.LCD;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Class defines robots catapult-functions.
 * 
 * @author santtu
 * 
 */

public class Katapultti {
    // Sets motor speed and angle.
    EV3LargeRegulatedMotor MotorC;
    int Speed = 500;
    int Angle = 60;

    /**
     * Constructor, creates new Motor-object
     */
    public Katapultti(){
        // Create new motor object.
        this.MotorC = new EV3LargeRegulatedMotor(MotorPort.A);
        updateScreen();
    }
        /**
         * Shoots catapult: speed:800, rotation angle:70.
         * rotates motor back to angle 0.
         */
    public void shoot (){
        MotorC.setSpeed(800);
        MotorC.rotate(70);
        MotorC.rotateTo(0);
    }
        /**
         * Shoots catapult int Speed: user sets(int), int Angle: user sets(int).
         */
    public void shoot2 (){
        MotorC.setSpeed(Speed);
        MotorC.rotate(Angle);
        MotorC.rotateTo(0);
    }
        /**
         * Increases motor speed +100 if speed is under 800 else speed is set to 800 (int).
         */
    public void addSpeed(){
        if (this.Speed < 800){
            this.Speed = this.Speed + 100;
        }else {
            this.Speed = 800;
        }
    }
        /**
         * Decelerates speed if speed is over 200 (int).
         */
    public void decSpeed(){
        if(this.Speed >= 200){
            this.Speed = this.Speed - 100;
        }
    }
        /**
         * Increases motor roration angle +5.
         */
    public void addAngle(){
        if(this.Angle < 90){
            this.Angle = this.Angle + 5;
        }
    }
        /**
         * Decreases motor rotate angle -5.
         */
    public void decAngle(){
        if(this.Angle > 10){
            this.Angle -= 5;
        }
    }
        /**
         * Screen shows current settings: speed(int), angle(int).
         */
    public void updateScreen() {
        LCD.clear(3);
        LCD.drawString("S: " + this.Speed + " A: " + this.Angle, 0, 3);
    }
}

