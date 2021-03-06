
package org.usfirst.frc.team2220.robot;

import org.usfirst.frc.team2220.robot.commands.DriveStraightForDistance;
import org.usfirst.frc.team2220.robot.commands.ExampleCommand;
import org.usfirst.frc.team2220.robot.commands.LeftStart.LStartLSwitch;
import org.usfirst.frc.team2220.robot.commands.LeftStart.LStartRSwitch;
import org.usfirst.frc.team2220.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team2220.robot.subsystems.ExampleSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();	
	public static final DrivetrainSubsystem DriveTrain = new DrivetrainSubsystem();
	
	public static OI oi;
	
	@SuppressWarnings("deprecation")
	NetworkTable table;
	

	Command autonomousCommand;
	
	
	SendableChooser<Command> chooser = new SendableChooser<>();
	Double[] defaultValue = new Double[0];
	
	DifferentialDrive ArtemisDrive;

	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void robotInit() {	
		oi = new OI();	
		
		//autonomousCommand = new DriveStraightForDistance(150);
		
		/*UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(240, 128);
		camera.setFPS(30);
		camera.setExposureManual(50);
		
		
		*/
		
		
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);	
		
		table = NetworkTable.getTable("GRIP/myContourValues");
		
		
	}
	
	/*
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		
		autoDecider();
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
					
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//Put Temperature
		SmartDashboard.putNumber("LeftMasterTalon Temp", DriveTrain.lDriveMaster.getTemperature());
		SmartDashboard.putNumber("RightMasterTalon Temp", DriveTrain.rDriveMaster.getTemperature());

		SmartDashboard.putNumber("navx Gyro Z",DriveTrain.navX.getRawGyroZ());
		SmartDashboard.putNumber("navx Angle",DriveTrain.navX.getAngle());
		SmartDashboard.putNumber("navx yaw",DriveTrain.navX.getYaw());
		
		SmartDashboard.putNumber("Left Pos",DriveTrain.getLeftPos());
		SmartDashboard.putNumber("Left RPM",DriveTrain.getLeftRPM());
		
		SmartDashboard.putNumber("Right Pos",DriveTrain.getRightPos());
		SmartDashboard.putNumber("Right RPM",DriveTrain.getRightRPM());
		
		//Number[] centerX = table.getNumberArray(defaultValue);
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void autoDecider() {
	
		
		
		String fieldData;
		fieldData = DriverStation.getInstance().getGameSpecificMessage();
		char switchSide = fieldData.charAt(0);
		
		if (switchSide == 'L') {
			autonomousCommand = new LStartLSwitch();
		} else if (switchSide == 'R') {
			autonomousCommand = new LStartRSwitch();
		}
		
		System.out.println(fieldData);
		
	}
	
	
}
