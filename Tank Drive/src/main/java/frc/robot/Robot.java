/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid; 
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
    /*
     * --- [1] Update CAN Device IDs and use WPI_VictorSPX where necessary ------
     */
    WPI_TalonSRX _rghtFront = new WPI_TalonSRX(4);
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(2);
    WPI_TalonSRX _elevator = new WPI_TalonSRX(6);

    DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rghtFront);

    Joystick joystick1 = new Joystick(0);
    Joystick joystick2 = new Joystick(2);

    XboxController xbox = new XboxController(1);

    Solenoid clawopen = new Solenoid(0);
    Solenoid clawclose= new Solenoid(1);
    

   // AnalogGyro gyro = new AnalogGyro(); //documentation says no parameters indicates FRC GYRO in RoboRio SPI slot
    

    @Override
    public void robotInit() {
        /* factory default values */
        _rghtFront.configFactoryDefault();
        _leftFront.configFactoryDefault();
        clawopen.set(true);
     //  gyro.reset(); //set current direction to zero degrees

    }

    @Override
        public void teleopPeriodic() 
        {
        
        _elevator.set(xbox.getRawAxis(1));

        _diffDrive.tankDrive(joystick1.getY(), joystick2.getY());

        //double angle = gyro.getAngle(); // get current heading
//SmartDashboard.putNumber("Gyro Angle:", angle);

        
        if (joystick1.getRawButton(2)==true  && clawopen.get()==false)
        {
            clawopen.set(true);
            clawclose.set(false);

        }
        else if (joystick1.getRawButton(2)==true && clawopen.get()==true)
        {
            clawclose.set(true);
            clawopen.set(false);
        }
        else
        {
            clawopen.set(false);
            clawclose.set(false);
        }

        }

        public void autonomous() 
        {

           /* gyro.reset();
            double Kp=1.0;  //set to constant, but could incorporate PID routine outputs
            while (isAutonomous()) 
            {
                double angle = gyro.getAngle(); // get current heading
                _diffDrive.arcadeDrive(-1.0, -angle*Kp); // drive towards heading 0
               // Timer.delay(0.004);
            }
            _diffDrive.arcadeDrive(0.0, 0.0);

            */
        }
    }