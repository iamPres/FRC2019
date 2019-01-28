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

public class NetOutput extends Subsystem {
	
	NetOutput(int user_input, NetworkTableInstance net){

		// Initialize network tables 
		NetworkTable ball_table = net.getTable("ball_data");
		NetworkTable tape_table = net.getTable("tape_data");

		public user_input = user_input;

		// Kill all processes and start selected script
		chooser(user_input);
		// Wait for startup (ms)
		wait(500);
	}
	
 	public double get_output_of_selected_action() {

		if (user_input == 0) {
			// Return network table value
			return get_data(ball_table);
		}

		if (user_input == 1) {	
			// Return network table value
			return get_data(tape_table);
		}
 	}

	public void chooser(int choice){
		net.putInt(choice);
	}
 }