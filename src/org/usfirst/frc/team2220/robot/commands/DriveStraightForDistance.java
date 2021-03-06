package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.Robot;
import org.usfirst.frc.team2220.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightForDistance extends Command{

	
	

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	double finalDistance;
	double currentDistance;
	double inputDistance;
	
	double reachedDistanceFINAL;
	double reachedEncDist;
	
	public DriveStraightForDistance(double Distance) {
	
		inputDistance = Math.abs(Distance);
		
	}


		// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			
			Robot.DriveTrain.resetEncoderPos();
			
		
			
			System.out.println(inputDistance);
			
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
		
			Robot.DriveTrain.incrementAllPos(inputDistance);
			//Robot.DriveTrain.DriveSet(0.2,- 0.2, true);
			System.out.println("COMMAND RUNNING");
		}
		// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			System.out.println(Robot.DriveTrain.setpointIsFinished());
			return Robot.DriveTrain.setpointIsFinished();
			
			
		}

		// Called once after isFinished returns true
		@Override
		protected void end() {
			
			reachedEncDist = Robot.DriveTrain.lDriveMaster.getSelectedSensorPosition(0);
			reachedDistanceFINAL = reachedEncDist / 1440 * (4 *  Math.PI) / 12;
			System.out.print(reachedDistanceFINAL);
			
			System.out.println("END RAN");
			Robot.DriveTrain.driveStop();
			
			
			
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
		}
	/*
	double finalDistance;
	double currentDistance;
	double inputDistance;
	
	double reachedDistanceFINAL;
	double reachedEncDist;
	

	public double howFar;
	public double howFast;
    public DriveStraightForDistance(double distance, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		howFar = distance;
    		howFast = Math.abs(power);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.DriveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    	Robot.DriveTrain.resetEncoderPos();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (howFar <= 0)
    	{
    	Robot.DriveTrain.DriveSet(howFast, -howFast, true);
    	}
    	else
    	{
        	Robot.DriveTrain.DriveSet(-howFast, howFast, true);
    	}
    	System.out.println("COMMAND RUNNING");
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs((Robot.DriveTrain.getLeftPos() + Robot.DriveTrain.getRightPos() ) / 2) >= Math.abs(howFar))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	

		reachedEncDist = Robot.DriveTrain.lDriveMaster.getSelectedSensorPosition(0) + Robot.DriveTrain.lDriveMaster.getSelectedSensorPosition(0) ) / 2;
		reachedDistanceFINAL = reachedEncDist / 1440 * (4 *  Math.PI) / 12;
		System.out.print(reachedDistanceFINAL);
    	
    	System.out.println("END RAN");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
}
	*/
		
		
}
