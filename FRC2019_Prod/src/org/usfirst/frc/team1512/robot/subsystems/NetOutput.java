package org.usfirst.frc.team1512.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class NetOutput extends Subsystem {	

	int input;

	NetOutput(int user_input){
		// Initialize network tables 
		NetworkTableInstance net = NetworkTableInstance.getDefault();
		NetworkTable ball_table = net.getTable("ball_data");
		NetworkTable tape_table = net.getTable("tape_data");

		input = user_input;

		// Kill all processes and start selected script
		chooser(input);
		// Wait for startup 
		Thread.sleep(500);
	}
	
 	public double get_output_of_selected_action() {
		if (input == 0) {
			// Return network table value
			return get_data(ball_table);
		}

		if (input == 1) {	
			// Return network table value
			return get_data(tape_table);
		}
 	}

	public void chooser(int choice){		
		// Prototype
		net.putInt(choice);
	}

	public double get_data(NetworkTable table){
		// Prototype
		return net.getDouble(table);
	}
 }