package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.NetOutput;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class autonomous extends Command {

    int setpoint = 320;
    double error;
    double reading;
    NetOutput obj;
    
	autonomous(){
		requires(Robot.driveTrain);
	}
	
 public autonomous(int choice) {
	// Initialize Subsystem Object
	obj = new NetOutput(choice);
 }

 protected void initialize() {
	Robot.driveTrain.stop();
}

 protected void execute(){
	// Store networktables output
	System.out.println("BOOBIES");
	double[] output = new double[3];
	output[0] = obj.get_output_of_selected_action();

	// PID
    double[] speeds = PID(output[0]);
    System.out.println(speeds);
	Robot.driveTrain.tankDrive(speeds[0], speeds[1]); //Turns each motor according to the error. 
 }

protected double[] PID(double output) {
    
	double[] returnVals = new double[2];
	reading = output; //Gets angle form Gyro
	error = reading - setpoint; //Calculates error based on the predefined reference point

	if (error < 0){
		returnVals[0] = -0.5;
		returnVals[1] = -0.5;
	}
	else if (error >= 0) {
		returnVals[0] = 0.5;
		returnVals[1] = 0.5;
    }
    else {
        returnVals[0] = 0;
        returnVals[1] = 0;
    }

	return returnVals;
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished() {
	return isTimedOut();
}

// Called once after isFinished returns true
protected void end() {
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
	end();
}

}