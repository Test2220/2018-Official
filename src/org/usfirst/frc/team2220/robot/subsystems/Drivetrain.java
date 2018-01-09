package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem{
	
	public WPI_TalonSRX lDriveMaster;
	public WPI_TalonSRX lDriveSlave;
	public WPI_TalonSRX rDriveMaster;
	public WPI_TalonSRX rDriveSlave;
	
	
	DifferentialDrive ArtemisDrive;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

	public Drivetrain() {
		
		//Master
		lDriveMaster = new WPI_TalonSRX(RobotMap.leftMaster);
		rDriveMaster = new WPI_TalonSRX(RobotMap.rightMaster);
		
		//Slave
		lDriveSlave = new WPI_TalonSRX(RobotMap.leftSlave);
		rDriveSlave = new WPI_TalonSRX(RobotMap.rightSlave);
		
		//Set follow
		lDriveSlave.follow(lDriveMaster);	
		rDriveSlave.follow(rDriveMaster);
		
		ArtemisDrive = new DifferentialDrive(lDriveMaster, rDriveMaster);
		
	}
	
	
	public void DriveSet(double leftSet, double rightSet, boolean deadzoneCase) {
		
		lDriveMaster.set(leftSet);
		rDriveMaster.set(rightSet);
		
	}
	
	public void ArcadeDrive(double ySet, double zSet, boolean deadzoneCase) {
		
		ArtemisDrive.arcadeDrive(ySet, zSet);
		
	}
	
	public void CurvatureDrive(double ySet, double zSet, boolean deadzoneCase, boolean isQuickTurn) {
		
		if (deadzoneCase) {
			
			double leftZoned = deadzone(ySet, 0.1);
			double rightZoned = deadzone(zSet, 0.1);
			
			ArtemisDrive.curvatureDrive(ySet, zSet, isQuickTurn);
		}  else {
			
			ArtemisDrive.curvatureDrive(ySet, zSet, isQuickTurn);
			
		}
		
		
	}
	
	
	public double deadzone(double output, double threshold) {
		if (output <= threshold && output >= - threshold) {
			return 0.;
			
		}
		return output;
	}
	
	
}
