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
		NetworkTable chooser_table = net.getTable("chooser_data");
		NetworkTable ball_table = net.getTable("ball_data");
		NetworkTable tape_table = net.getTable("tape_data");

		input = user_input;

		// Kill all processes and start selected script
		chooser(chooser_table, input);
		// Wait for startup 
		Thread.sleep(500);
	}
	
 	public double get_output_of_selected_action() {
		//Value 0 stops all scripts

		if (input == 1) {
			// Return network table value
			return get_data(tape_table);
		}
		else if (input == 2) {	
			// Return network table value
			return get_data(ball_table);
		}
 	}

	public void chooser(NetworkTable table, int choice){		

		table.putNumber("Choice", choice);
	}

	public double get_data(NetworkTable table){

		return table.getNumber("midpoint", 0.0); //0.0 is default 
	}
 }