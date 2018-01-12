package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.DriveStraightForDistance;
import org.usfirst.frc.team2220.robot.commands.DriveWithXBox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	

	Joystick driverStick = new Joystick(0);
	Joystick climberStick = new Joystick(1);
	
	
	Button tankDrive = new JoystickButton(driverStick, 5);
	Button climberButton = new JoystickButton(climberStick, 5);
	Button autoTurnButton = new JoystickButton(driverStick, 11);
	
	Button driveToDistanceButton = new JoystickButton(driverStick, 2);
	
	public Joystick getDriverStick() { 
		
		return driverStick;
		
	}
	
	public Joystick getClimberStick() {
		
		return climberStick;
		
	}
	
	public OI(){ 
		
		tankDrive.whenPressed(new DriveWithXBox());
		driveToDistanceButton.whenPressed(new DriveStraightForDistance(100));
		
	}

	

	
}
