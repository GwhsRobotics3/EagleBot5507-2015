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
	
	final int MOTOR_LEFT = 0;
	final int MOTOR_RIGHT = 1;
	final int MOTOR_LIFTER = 2;
	
	final int LEFT_JOYSTICK = 1;
	final int RIGHT_JOYSTICK = 2;
	
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	CANTalon motorLeft = new CANTalon(MOTOR_LEFT);
	CANTalon motorRight = new CANTalon(MOTOR_RIGHT);
	CANTalon motorLifter = new CANTalon(MOTOR_LIFTER);
	Compressor compressor = new Compressor();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(MOTOR_LEFT,MOTOR_RIGHT);
    	compressor.stop();
    	leftStick = new Joystick(LEFT_JOYSTICK);
    	rightStick = new Joystick(RIGHT_JOYSTICK);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
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
        myRobot.arcadeDrive(leftStick);
   		if(leftStick.getRawButton(LIFTERUP))
        {
   			motorLifter.set(1);
        }
   		
   		if(leftStick.getRawButton(LIFTERDOWN))
   		{
   			motorLifter.set(-1);
   		}
   		
   		if(leftStick.getRawButton(TURNLEFT))
   		{
   			motorLeft.set(1);
   		}
   		/*
   		if(leftstick.getRawButton(2))
   		
   		{
   			for(int i = 0; i < 50; i++)
   			{
   				myRobot.drive(outputMagnitude, curve);;
   			}
   		}
   		*/
   		
   		}
   		
    	
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
   
   
}
l