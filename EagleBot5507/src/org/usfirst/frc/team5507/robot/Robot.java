package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Compressor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Buttons
	final int GRABBER = 1;
	final int LIFTERUP = 5;
	final int LIFTERDOWN = 6;
	final int TURNRIGHT = 3;
	final int TURNLEFT = 4;
	final int RESET = 7;
	final int LIGHTS = 8;
	
	final int MOTOR_PORT_1 = 1;
	final int MOTOR_PORT_0 = 0;
	
	RobotDrive myRobot;
	Joystick leftstick;
	Joystick rightStick;
	CANTalon motor1 = new CANTalon(MOTOR_PORT_1);
	CANTalon motor2 = new CANTalon(MOTOR_PORT_0);
	CANTalon motor3 = new CANTalon(2);
	int autoLoopCounter;
	Compressor compressor1 = new Compressor();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(MOTOR_PORT_0,MOTOR_PORT_1);
    	compressor1.stop();
    	leftstick = new Joystick(MOTOR_PORT_0);
    	rightStick = new Joystick(2);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	myRobot.drive(.05,0);
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        
    }
    
    /**
     * This function is called periodically during operator control
     */
	public void teleopPeriodic() 
	{
        myRobot.arcadeDrive(leftstick);
   		if(leftstick.getRawButton(1))
        {
   			for(int i = 0; i < 50; i++)
   			{
   				myRobot.drive(-.1, 0);
   			}
        }
   		if(leftstick.getRawButton(2))
   		{
   			for(int i = 0; i < 50; i++)
   			{
   				myRobot.drive(outputMagnitude, curve);;
   			}
   		}
   		
   		}
   		
    	
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
   
   
}
