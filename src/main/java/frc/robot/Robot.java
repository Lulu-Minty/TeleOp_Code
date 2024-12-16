// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
/* 
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with split
 * arcade steering and an Xbox controller.
 */
public class Robot extends TimedRobot {


  private final Victor m_leftMotor = new Victor(0);
  private final Victor m_rightMotor = new Victor(6);
  //the F is put in front to show that it is the follower motor
  private final Victor f_leftMotor = new Victor (1);
  private final Victor f_rightMotor = new Victor (7);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);
  private final XboxController m_driverController = new XboxController(0);
  

  /** Called once at the beginning of the robot program. */
  public Robot() {
    m_leftMotor.addFollower(f_leftMotor);
    m_rightMotor.addFollower(f_rightMotor);
    
    // We need to invert one  side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);
    m_robotDrive.setSafetyEnabled(false);

  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }
  
  @Override
  public void disabledPeriodic() {}

  @Override
  public void teleopPeriodic() {
    // Drive with split arcade drive.
    // That means that the Y axis of the left stick moves forward
    // and backward, and the X of the right stick turns left and right.
    m_robotDrive.arcadeDrive(-m_driverController.getRawAxis(1), -m_driverController.getRawAxis(4));
  }
}