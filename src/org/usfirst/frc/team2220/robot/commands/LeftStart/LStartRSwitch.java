package org.usfirst.frc.team2220.robot.commands.LeftStart;

import org.usfirst.frc.team2220.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LStartRSwitch extends Command{


	public LStartRSwitch() {
		
		requires(Robot.DriveTrain);
		
	}
	
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		Robot.DriveTrain.DriveSet(0.2, 0, false);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
