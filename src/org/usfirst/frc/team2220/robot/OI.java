package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.DriveWithXBox;
import org.usfirst.frc.team2220.robot.commands.MoveCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	
	public int inputFt = 5;
	
	public double finalTick = (inputFt * 12) / (4*Math.PI) * 1440 ;
	
	
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
		//driveToDistanceButton.whenPressed(new DriveStraightForDistance(finalTick));
		driveToDistanceButton.whenPressed(new MoveCommand(0.1, 20));
		
	}

	

	
}
