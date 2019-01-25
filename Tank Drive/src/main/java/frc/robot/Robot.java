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

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid; 

public class Robot extends TimedRobot {
    /*
     * --- [1] Update CAN Device IDs and use WPI_VictorSPX where necessary ------
     */
    WPI_TalonSRX _rghtFront = new WPI_TalonSRX(2);
    WPI_TalonSRX _leftFront = new WPI_TalonSRX(1);
    WPI_TalonSRX _lifter = new WPI_TalonSRX(3);

    DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rghtFront);

    Joystick joystick1 = new Joystick(0);
    Joystick joystick2 = new Joystick(1);

    Solenoid clawopen = new Solenoid(1);
    Solenoid clawclose= new Solenoid(2);
    

    @Override
    public void robotInit() {
        /* factory default values */
        _rghtFront.configFactoryDefault();
        _leftFront.configFactoryDefault();
        clawopen.set(true);

    }

    @Override
    public void teleopPeriodic() {

      _diffDrive.tankDrive(joystick1.getY(), joystick2.getY());

      
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
    }