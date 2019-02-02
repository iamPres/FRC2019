package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.autonomous;
import frc.robot.subsystems.NetOutput;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class ButtonSelect extends CommandGroup {

    XboxController xbox = RobotMap.xboxController;

    protected void execute() {

    if (xbox.getAButtonPressed()) {
        addSequential(new autonomous(0));
    }
    if (xbox.getXButtonPressed()) {

        addSequential(new autonomous(1));
        new autonomous(1);
    }
    if (xbox.getBButtonPressed()) {
        
        addSequential(new autonomous(2));

        new autonomous(2);
    }
}
}