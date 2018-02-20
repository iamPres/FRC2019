package org.usfirst.frc.team1512.robot.commands;

import org.usfirst.frc.team1512.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetGrabber extends Command {

	boolean OoC;
	/**
	 * 
	 * @param type false is open, true is close
	 */
    public SetGrabber(boolean type) {
    	OoC = type;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.grabber);
    	setTimeout(0.1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OoC == false) {
    		Robot.grabber.open();
    	}
    	else {
    		Robot.grabber.close();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.open();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}