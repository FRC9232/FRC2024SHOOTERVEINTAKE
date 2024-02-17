// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.IntakeSetCommand;
import frc.robot.commands.ShooterSetCommand;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

    IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    ColorSubsystem colorSensore = new ColorSubsystem();
    ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

    CommandJoystick driverController = new CommandJoystick(0);
    CommandJoystick throttleController = new CommandJoystick(2);
    
    PS4Controller driverPs4Controller = new PS4Controller(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    Trigger intakeTrigger = driverController.button(6);
    Trigger intakeReversedTrigger = driverController.button(4);
    Trigger intakeToShooterTrigger = driverController.button(8);
    Trigger intakeAndShooterStopTrigger = driverController.button(3);
    
    Trigger shooterSpeakerTrigger = driverController.button(7);
    Trigger shooterAmphTrigger = driverController.button(5);
    
    intakeTrigger.onTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, false,false,true));
    intakeReversedTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, true,false,false));
    intakeToShooterTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, false,true,false));
    intakeAndShooterStopTrigger.onFalse(new IntakeSetCommand(intakeSubsystem, colorSensore, false, false, false,false));
    intakeAndShooterStopTrigger.onFalse(new ShooterSetCommand(shooterSubsystem, colorSensore, false, false));

    shooterAmphTrigger.onTrue(new ShooterSetCommand(shooterSubsystem, colorSensore, true,false));
    shooterSpeakerTrigger.onTrue(new ShooterSetCommand(shooterSubsystem, colorSensore, true, true));
    
    
    //basılı tutarken çalışan intake
    /* 
    intakeTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, false,false,true));
    intakeReversedTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, true,false,false));
    intakeToShooterTrigger.whileTrue(new IntakeSetCommand(intakeSubsystem, colorSensore, true, false,true,false));
    intakeTrigger.or(intakeToShooterTrigger).or(intakeReversedTrigger).whileFalse(new IntakeSetCommand(intakeSubsystem, colorSensore, false, false, false,false));
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
}
