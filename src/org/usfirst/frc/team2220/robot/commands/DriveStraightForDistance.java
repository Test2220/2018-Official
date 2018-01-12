package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.Robot;
import org.usfirst.frc.team2220.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightForDistance extends Command{

	double finalDistance;
	double currentDistance;
	double inputDistance;
	
	public DriveStraightForDistance(int Distance) {
	
		inputDistance = Math.abs(Distance);
		
	}


		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			
			Robot.DriveTrain.resetEncoderPos();
			
			Robot.DriveTrain.incrementAllPos(inputDistance);
			
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			
			
			
		}
		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			
			return Robot.DriveTrain.setpointIsFinished();
			
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
