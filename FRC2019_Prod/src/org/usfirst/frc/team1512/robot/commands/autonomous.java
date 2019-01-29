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
	obj = NetOutput(choice);
 }

 protected void initialize() {
	Robot.driveTrain.stop();
}

 public execute(){
	// Store networktables output
	output = obj.get_output_of_selected_action();

	// PID
	double[] speeds = PID(output);
		Robot.driveTrain.tankDrive(speeds[0], speeds[1]); //Turns each motor according to the error. 
 }

protected double[] PID(double output) {
	double[] returnVals = new double[2];
	reading = output; //Gets angle form Gyro
	error = reading - setpoint; //Calculates error based on the predefined reference point
	newVal = P*error; //Multiplies the error by a constant

	// ie the gyro is reading a value less then 180 degrees, meaning the robot needs to be adjusted to the right
	// note that only a value of ~0.5 for speed should be fed in for best results, unless newVal is miniscule
	if (newVal > 0.0) {
		if (!((newVal + speed) > 1.0)) {
			// left drive needs to be more powerful
			returnVals[0] = newVal + speed;
			returnVals[1] = -1.0 * (speed - newVal);
			return returnVals;
		}
		// the weird case in which the gyro is reading a value so abnormal that either something is wrong or serious corrections need to be made
		// for now drive equal power to both motors
		else {
			returnVals[0] = speed;
			returnVals[1] = -1.0 * speed;
			return returnVals;
		}
	}
	// ie the gyro is reading a value greater then 180 degrees, meaning the robot needs to be adjusted to the left
	else {
		newVal = Math.abs(newVal);
		if (!((newVal + speed) > 1.0)) {
			// right drive needs to be more powerful
			returnVals[0] = speed - newVal;
			returnVals[1] = -1.0 * (speed + newVal);
			return returnVals;
		}
		// the weird case in which the gyro is reading a value so abnormal that either something is wrong or serious corrections need to be made
		// for now drive equal power to both motors
		else {
			returnVals[0] = speed;
			returnVals[1] = -1.0 * speed;
			return returnVals;
		}
	}

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