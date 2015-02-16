package org.usfirst.frc.team620.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive class.
 */
public class Robot extends SampleRobot {
	
    GyroITG3200 gyro3200;

     public Robot() {	
         gyro3200 = new GyroITG3200(I2C.Port.kOnboard);
     }
        
    public void robotInit()
    {
        System.out.println("robotInit");
    }
    
    public void disabled()
    {
        int cX, cY, cZ;
        int calX = 0, calY = 0, calZ = 0;
        double cR, calR = 0;
 
        gyro3200.initialize();
        gyro3200.reset();
        Timer.delay(0.05); // wait 50 msecs after reset
        
        System.out.println("Gyro sample rate default " + gyro3200.getRate());
        gyro3200.setRate((byte) 0);
        System.out.println("Gyro sample rate current " + gyro3200.getRate());
       
        System.out.println("Gyro bandwidth default " + gyro3200.getDLPFBandwidth());
        gyro3200.setDLPFBandwidth((byte) 0); 
        System.out.println("Gyro bandwidth current " + gyro3200.getDLPFBandwidth());
      
        gyro3200.setStandbyYEnabled(true);
        gyro3200.setStandbyZEnabled(true);
        
        for (int i=0; i < 100; i++) {

        	calX += gyro3200.getRotationX();	
        	calY += gyro3200.getRotationY();	
        	calZ += gyro3200.getRotationZ();	
        	calR += gyro3200.pidGet();
        	
        	calX /= 100;
           	calY /= 100;
           	calZ /= 100;
           	calR /= 100;
        }	
        System.out.println("calX: " + calX + " calY: " + calY + " calZ: " + calZ + " calR: " + calR);  	
        
        for (int i=0; i < 100; i++) {
        	
 /*
        	cX = (360 - gyro3200.getRotationX()) % 360;	
            cY = (360 - gyro3200.getRotationY()) % 360;	
            cZ = (360 - gyro3200.getRotationZ()) % 360;	
            cRotation = gyro3200.pidGet();
*/
          	cX = gyro3200.getRotationX();	
            cY = gyro3200.getRotationY();	
            cZ = gyro3200.getRotationZ();	
            cR = gyro3200.pidGet();
/*     	
            cX -= calX;
            cY -= calY;
            cZ -= calZ;
            cR -= calR;
*/
            
            System.out.println("X: " + cX + " Y: " + cY + " Z: " + cZ + " R: " + cR);
            Timer.delay(1);	// wait 1 second
        }
    }

    public void operatorControl() {
            System.out.println("Operator Control")  ; 
    		Timer.delay(0.01);	// wait 10ms to avoid hogging CPU cycles
    }
}
