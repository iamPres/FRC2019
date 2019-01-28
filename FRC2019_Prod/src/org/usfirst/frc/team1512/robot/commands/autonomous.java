package org.usfirst.frc.team1512.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
package org.usfirst.frc.team1512.robot.subsystems;

import org.usfirst.frc.team1512.robot.Robot;
import org.usfirst.frc.team1512.robot.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class autonomous extends CommandGroup {
	NetworkTableValue turn;
	NetworkTableInstance net = NetworkTableInstance.getDefault();
	public int error;
	public int thresh = 1.0;
	
	autonomous(){
		requires(Robot.driveTrain);
	}
	
 public autonomous(int choice) {
	 output = subsystems.NetOutput(choice, net);
 }

 public execute(){
	 if (error > thresh) {
	 	PID(output.get_output_of_selected_action());
	 }
 }

 public double PID(){
	 // PID stuff
 }

}