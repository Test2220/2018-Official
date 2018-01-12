package org.usfirst.frc.team2220.robot.subsystems;

import org.usfirst.frc.team2220.robot.Robot;
import org.usfirst.frc.team2220.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Subsystem{
	
	public TalonSRX lDriveMaster;
	public TalonSRX lDriveSlave;
	public TalonSRX rDriveMaster;
	public TalonSRX rDriveSlave;

	DifferentialDrive ArtemisDrive;
	
	public AHRS navX;
	
	public static FeedbackDevice QuadEncoder;
	
	double leftPos;
	double rightPos;
	
	double leftRPM;
	double rightRPM;
	
	double ticksPerRev = 1440;
	double cyclesPerRev = 360;
	
	
	public static double rDriveMotorSetpoint = 0;
	public static double lDriveMotorSetpoint = 0;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	
	}
	

	public DrivetrainSubsystem() {
		
		navX = new AHRS(SPI.Port.kMXP);

		//Master
		lDriveMaster = new TalonSRX(RobotMap.leftMaster);
		rDriveMaster = new TalonSRX(RobotMap.rightMaster);
		
		
		//Slave
		lDriveSlave = new TalonSRX(RobotMap.leftSlave);
		rDriveSlave = new TalonSRX(RobotMap.rightSlave);
		
		
		//Set follow
		lDriveSlave.follow(lDriveMaster);	
		rDriveSlave.follow(rDriveMaster);
		
		lDriveMaster.setInverted(true);
		rDriveMaster.setInverted(true);
		
		lDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		rDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		//Set PID
		double kP = 2.0, kI = 0.0015, kD = 0.0;
		
		rDriveMaster.config_kP(0, kP, 10);
		rDriveMaster.config_kI(0, kI, 10);
		rDriveMaster.config_kD(0, kD, 10);
		
		lDriveMaster.config_kP(0, kP, 10);
		lDriveMaster.config_kI(0, kI, 10);
		lDriveMaster.config_kD(0, kD, 10);
		
		//Set Motion Magic Profiles
		int accel = 200, cruiseVel = 400;
		
		lDriveMaster.configMotionAcceleration(accel, 10);
		lDriveMaster.configMotionCruiseVelocity(cruiseVel, 10);
		
		rDriveMaster.configMotionAcceleration(accel, 10);
		rDriveMaster.configMotionCruiseVelocity(cruiseVel, 10);
		
	}
	
	//-------------------TYPES OF DRIVE-----------------//
	
	
	//Percent VBus DriveSet
	public void DriveSet(double leftSet, double rightSet, boolean deadzoneCase) {
		if (deadzoneCase) {
			
		leftSet = deadzone(leftSet, 0.1);
		rightSet = deadzone(rightSet, 0.1);	
		
		lDriveMaster.set(ControlMode.PercentOutput, leftSet);
		rDriveMaster.set(ControlMode.PercentOutput,rightSet);
		
		}
	}
	
	//Arcade Drive if Necessary
	public void ArcadeDrive(double ySet, double zSet, boolean deadzoneCase) {
		
		ArtemisDrive.arcadeDrive(ySet, zSet);
		
	}
	
	//Curvature (Cheesy) drive 
	public void CurvatureDrive(double ySet, double zSet, boolean deadzoneCase, boolean isQuickTurn) {
		
		if (deadzoneCase) {
			
			double leftZoned = deadzone(ySet, 0.2);
			double rightZoned = deadzone(zSet, 0.2);
			
			ArtemisDrive.curvatureDrive(ySet, zSet, isQuickTurn);
		}  else {
			
			ArtemisDrive.curvatureDrive(ySet, zSet, isQuickTurn);
			
		}
		
		
	}
	
	//Position Increments
	public void incrementRPosition(double x)
	{
		rDriveMotorSetpoint += x;
		rDriveMaster.set(ControlMode.MotionMagic,rDriveMotorSetpoint);
	}

	public void incrementLPosition(double x)
	{
		lDriveMotorSetpoint -= x;
		lDriveMaster.set(ControlMode.MotionMagic,lDriveMotorSetpoint);
	}

	public void incrementAllPos(double x)	
	{
		incrementRPosition(x);
		incrementLPosition(x);
	}
	
	
	//-----------------Encoder Values---------------//
	
	//Get Left Encoder Ticks
	public double getLeftPos() {
		
		leftPos = lDriveMaster.getSensorCollection().getQuadraturePosition();
		return leftPos;
		
	}
	
	
	//Get Right Encoder Ticks
	public double getRightPos() {
		
		rightPos = rDriveMaster.getSensorCollection().getQuadraturePosition();
		return rightPos;
		
	}
	
	
	//Get Left RPM
	public double getLeftRPM() {
		
		leftRPM = lDriveMaster.getSelectedSensorVelocity(0)* (600/1440);
		return leftRPM;
		
	}
	
	///Get Right RPM
	public double getRightRPM() {	
		
		rightRPM = rDriveMaster.getSelectedSensorVelocity(0)* (600/1440);
		return rightRPM;
		
	}
	
	public void resetEncoderPos()
	{
		rDriveMotorSetpoint = rDriveMaster.getSelectedSensorPosition(0);
		lDriveMotorSetpoint = lDriveMaster.getSelectedSensorPosition(0);
	}
	
	
	/*//Non Functional Distance Set
	public void distanceSet(double input) { 
		
		lDriveMaster.set(ControlMode.Position, -input);
		rDriveMaster.set(ControlMode.Position, input);
		
	}
	*/
	
	//Check whether Set point is finished
	public boolean setpointIsFinished() {
		
		return  lDriveMaster.getClosedLoopError(0) < 30 && rDriveMaster.getClosedLoopError(0) < 30;
		
	}
	
	
	//Super Basic Deadzone function
	public double deadzone(double output, double threshold) {
		if (output <= threshold && output >= - threshold) {
			return 0.;
			
		}
		return output;
	}
	
	
	
}
