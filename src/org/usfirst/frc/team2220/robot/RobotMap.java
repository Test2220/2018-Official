package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//All Correct Vals for Artemis bot
	/*public static final int leftMaster = 1,
			leftSlave = 2,
			rightMaster = 3,
			rightSlave = 4;
			
			*/
			
	public static final  Solenoid rightSol1 = new Solenoid(6);
	public static final Solenoid rightSol2 = new Solenoid(7);
	public static final Solenoid leftSol1 = new Solenoid(4);
	public static final Solenoid leftSol2 = new Solenoid(5);
	
	// Left Master and Right Master vals corrent for IkeaEncBot --- Slaves must be updated --- Have to correct orientation of drive
	public static final int leftMaster = 15,
			leftSlave = 3,	
			rightMaster = 2,
			rightSlave = 4;
	
}
