package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NetOutput extends Subsystem {	

	int input;
	NetworkTableInstance net = NetworkTableInstance.getDefault();
	NetworkTable chooser_table = net.getTable("chooser_data");
	NetworkTable ball_table = net.getTable("ball_data");
	NetworkTable tape_table = net.getTable("tape_data");

	public NetOutput(int user_input){
		// Initialize network tables 
		

		input = user_input;

		// Kill all processes and start selected script
		chooser(chooser_table, input);
		// Wait for startup 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		return 0;
 	}

	public void chooser(NetworkTable table, int choice){		
		NetworkTableEntry choice_entry = table.getEntry("choice");
		choice_entry.setDouble(choice);
	}

	public double get_data(NetworkTable table){
		DriverStation.reportError("Output = "+Double.toString(table.getEntry("midpoint").getDouble(0.0)), false);
		return table.getEntry("midpoint").getDouble(0.0);
	}

	@Override
	protected void initDefaultCommand() {

	}
 }