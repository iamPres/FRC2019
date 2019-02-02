/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1512.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team1512.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team1512.robot.commands.LiftGrabber;
import org.usfirst.frc.team1512.robot.commands.autonomous;
import org.usfirst.frc.team1512.robot.subsystems.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 * 
	 * Note that its ok to initialize the subsystems here because their commands won't be
	 * scheduled by the Scheduler until teleopPeriodic starts
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();

		driveTrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		m_oi = new OI();
	}

	/**
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

	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called at the start of autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called at the start of operator control.
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		if (m_oi.xbox.getBButtonPressed() == true) {
			Scheduler.getInstance().add(new autonomous(0));
		}
		if (m_oi.xbox.getXButtonPressed() == true) {
			Scheduler.getInstance().add(new autonomous(1));
		}
		if (m_oi.xbox.getBButtonPressed() == true) {
			Scheduler.getInstance().add(new autonomous(2));
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
