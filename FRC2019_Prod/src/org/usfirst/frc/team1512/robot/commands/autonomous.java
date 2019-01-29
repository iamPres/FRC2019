package org.usfirst.frc.team1512.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;

import org.usfirst.frc.team1512.robot.Robot;
import org.usfirst.frc.team1512.robot.RobotMap;
import org.usfirst.frc.team1512.robot.commands.SpinRobotAuto;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class autonomous extends CommandGroup {
	NetworkTableInstance net = NetworkTableInstance.getDefault();
	
	autonomous(){
		requires(Robot.driveTrain);
	}
	
 public autonomous(int choice) {
	// Initialize Subsystem Object
	output = NetOutput(choice, net);
 }

 public execute(){
	// Pass network tables output into PID
	output = output.get_output_of_selected_action();
	SpinRobotAuto(output);
 }

}