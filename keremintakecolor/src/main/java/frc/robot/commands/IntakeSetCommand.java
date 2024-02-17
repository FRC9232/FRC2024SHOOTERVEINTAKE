package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSetCommand extends Command{
    
    private final IntakeSubsystem intakeSubsystem;
    private final ColorSubsystem colorSubsystem;
    private  boolean open;
    private boolean isReversed;
    private boolean intakeToShooter;
    private boolean forward;
    public IntakeSetCommand(IntakeSubsystem intakeSubsystem,ColorSubsystem colorSubsystem, boolean open, boolean isReversed, boolean intakeToShooter, boolean forward){
        this.intakeSubsystem = intakeSubsystem;
        this.colorSubsystem = colorSubsystem;
        this.open = open;
        this.isReversed = isReversed;
        this.intakeToShooter = intakeToShooter;
        this.forward = forward;
        addRequirements(intakeSubsystem, colorSubsystem);
    }
    public void execute(){
        if (forward){
            open = true;
        }
        if (isReversed){
            intakeSubsystem.setIntakeReversed();
        }
        else if (intakeToShooter){
            intakeToShooter();
        }
        else{
            if(colorSubsystem.a == false){
                open = false;
                
            }
           
           intakeSubsystem.setIntake(open);
        }
         SmartDashboard.putBoolean("adegecolor", colorSubsystem.a);
         SmartDashboard.putBoolean("opendeger", open);
    }
    public void intakeClose(){
        intakeSubsystem.setIntake(false);
    }
    public void intakeToShooter(){
        intakeSubsystem.intakeToShooter();

    }
    public void end(){
        intakeSubsystem.setIntake(false);
    }
}
