package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ShooterSetCommand extends Command {
    
    private final ShooterSubsystem shooterSubsystem;
    private final ColorSubsystem colorSubsystem;
    private boolean open;
    private boolean speaker;
    
    public ShooterSetCommand(ShooterSubsystem shooterSubsystem,ColorSubsystem colorSubsystem, boolean open, boolean speaker){
        this.shooterSubsystem = shooterSubsystem;
        this.colorSubsystem = colorSubsystem;
        this.open = open;
        this.speaker = speaker;
        addRequirements(shooterSubsystem, colorSubsystem);
    }
    public void execute(){
        
        if(!open){
            shooterSubsystem.setShooter(false);
        }
        else{
            if(speaker){
                shooterSubsystem.setShooter(true);
            }
            else{
                shooterSubsystem.amphShoot();
            }
        }
        
    }
    
    
    
    public void StartShooter(){
       
        shooterSubsystem.setShooter(true);
    }













 }