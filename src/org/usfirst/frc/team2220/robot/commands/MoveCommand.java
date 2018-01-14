package org.usfirst.frc.team2220.robot.commands;

import org.usfirst.frc.team2220.robot.Robot;
import org.usfirst.frc.team2220.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCommand extends Command{


	private double speed;
	private double distMeters;
	
	private final double ERROR_SCALING_CONST_P = 1;
	
	private DrivetrainSubsystem DriveTrain;
	
	private double initialLeftCount, initialRightCount;
	
    public MoveCommand(double speed, double distMeters) {
        requires(Robot.DriveTrain);
        DriveTrain = Robot.DriveTrain;
        this.speed = speed;
        this.distMeters = distMeters;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialLeftCount = DriveTrain.getLeftPos();
    	initialRightCount = DriveTrain.getRightPos();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftDelta = DriveTrain.getLeftPos() - initialLeftCount;
    	double rightDelta = DriveTrain.getRightPos() - initialRightCount;
    	double error = leftDelta - rightDelta;
    	double correctionFactor = error * ERROR_SCALING_CONST_P;
    	DriveTrain.DriveSet(speed + correctionFactor, speed - correctionFactor, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double leftDelta = DriveTrain.getLeftPos() - initialLeftCount;
    	double rightDelta = DriveTrain.getRightPos() - initialRightCount;
    	if ((leftDelta + rightDelta) / 2 > distMeters) {
    		return true;
    	}
    	return false;
}
	
}
