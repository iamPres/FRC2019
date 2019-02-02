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
	autonomous(){
		requires(Robot.driveTrain);
	}
	
 public autonomous(int choice) {
	// Initialize Subsystem Object
	obj = Robot.NetOutput(choice);
 }

 protected void initialize() {
	Robot.driveTrain.stop();
}

 public execute(){
	// Store networktables output
	double[] output = new double[3];
	output = obj.get_output_of_selected_action();

	// PID
	double[] speeds = PID(output[1]);
	Robot.driveTrain.tankDrive(speeds[0], speeds[1]); //Turns each motor according to the error. 
 }

protected double[] PID(double output) {
	int setpoint = 0;
	double[] returnVals = new double[2];
	reading = output; //Gets angle form Gyro
	error = reading - setpoint; //Calculates error based on the predefined reference point
	newVal = P*error; //Multiplies the error by a constant

	if (error < 0){
		returnVals[0] = -0.5;
		returnVals[1] = 0.5;
	}
	else {
		returnVals[0] = 0.5;
		returnVals[1] = -0.5;
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