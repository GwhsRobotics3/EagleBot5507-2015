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
public class Robot extends IterativeRobot
{
	//Buttons

	final int GRABBER = 1;
	final int LIFTERLEVELUP = 6;
	final int LIFTERLEVELDOWN = 4;
	final int TURNRIGHT = 3;
	final int TURNLEFT = 4;
	final int RESET = 7;
	final int LIGHTS = 8;
	final int LIFTERUP = 5;
	final int LIFTERDOWN = 3;
	
	final int MOTOR_LEFT = 0;
	final int MOTOR_RIGHT = 1;
	final int MOTOR_LIFTER = 2;
	
	final int LEFT_JOYSTICK = 0;
	final int RIGHT_JOYSTICK = 1;
	
	final long ELAPSE_LIFT = 0; //need to change 
	final long ELAPSE_TURN = 0; //need to change
	
	RobotDrive myRobot;
	
	Joystick leftStick;
	Joystick rightStick;
	
	CANTalon motorLeft = new CANTalon(MOTOR_LEFT);
	CANTalon motorRight = new CANTalon(MOTOR_RIGHT);
	CANTalon motorLifter = new CANTalon(MOTOR_LIFTER);
	
	Compressor compressor = new Compressor();
	
	Timer timer = new Timer();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
    	myRobot = new RobotDrive(MOTOR_LEFT,MOTOR_RIGHT);
    	compressor.stop();
    	leftStick = new Joystick(LEFT_JOYSTICK);
    	rightStick = new Joystick(RIGHT_JOYSTICK);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit()
    {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit()
    {
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	
    	boolean lifterUpState = false;
    	boolean lifterDownState = false;
    	
        LifterState lifterState = STOPPED;
    }
    
    /**
     * This function is called periodically during operator control
     */
	public void teleopPeriodic() 
	{
		myRobot.arcadeDrive(leftStick);
		
		//lifter up 1 level
     	if(leftStick.getRawButton(LIFTERLEVELUP))
        {
     		timer.start();
     		while(timer.get()<ELAPSE_LIFT)
     		{
     			myRobot.arcadeDrive(leftStick);
     			motorLifter.set(1);
     		}
     		timer.stop();
     		timer.reset(); 
   			
        }
     	
   		//lifter down 1 level
   		if(leftStick.getRawButton(LIFTERLEVELDOWN))
   		{
   			timer.start();
     		while(timer.get()<ELAPSE_LIFT)
     		{
     			myRobot.arcadeDrive(leftStick);
     			motorLifter.set(-1);
     		}
     		timer.stop();
     		timer.reset();
   		}
   		
   		//lifter go up
   		if(leftStick.getRawButton(LIFTERUP))
   		{
   			if(state == STOPPED)
   			{
   				motorLifter.set(1);
   				lifterState = UP;
   			}
   			else
   			{
   				motorLifter.set(0);
   				lifterState = STOPPED;
   			}
   			
   			/*
   			if(!lifterUpState)
   			{
   				motorLifter.set(1);
   				lifterUpState = true;
   			}
   			else
   			{
   				motorLifter.set(0);
   				lifterUpState = false;
   			}
   			*/
   		}
   		
   		//lifter go down
   		if(leftStick.getRawButton(LIFTERDOWN))
   		{
   			if(state == STOPPED)
   			{
   				motorLifter.set(-1);
   				lifterState = DOWN;
   			}
   			else
   			{
   				motorLifter.set(0);
   				lifterState = STOPPED;
   			}
   			
   			/*
   			if(!lifterDownState)
   			{
   				motorLifter.set(1);
   				lifterDownState = true;
   			}
   			else
   			{
   				motorLifter.set(0);
   				lifterDownState = false;
   			}
   			*/
   		}
   		
   		//robot turn left 45 degrees
   		if(leftStick.getRawButton(TURNLEFT))
   		{
   			timer.start();
   			while(timer.get()<ELAPSE_TURN)
   			{
   				myRobot.drive(0,-1);
   			}
   			timer.stop();
   			timer.reset();
   		}
   		
   		//robot turn right 45 degress
   		if(leftStick.getRawButton(TURNRIGHT))
   		{
   			timer.start();
   			while(timer.get()<ELAPSE_TURN)
   			{
   				myRobot.drive(0,1);
   			}
   			timer.stop();
   			timer.reset();
   		}
   	}
   		
    	
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    	LiveWindow.run();
    }  
}
