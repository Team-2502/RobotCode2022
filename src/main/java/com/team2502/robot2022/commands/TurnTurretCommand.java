package com.team2502.robot2022.commands;

import com.team2502.robot2022.subsystems.TurretSubsystem;
import com.team2502.robot2022.util.Trapezoidal;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.team2502.robot2022.Constants.Subsystem.Turret;

public class TurnTurretCommand extends CommandBase {

    TurretSubsystem turret;
    Joystick operator;
    Trapezoidal trapezoidal;

    //TurnTurretCommand is created here. requires the Subsystem and the joystick
    public TurnTurretCommand(TurretSubsystem turret, Joystick operator){
        //sets turret and operator to values given from command
        this.turret = turret;
        this.operator = operator;
        trapezoidal = new Trapezoidal(4); // prevent driver from over-accelerating

        //only one command can use the subsystem at a time
        addRequirements(turret);
    }

    @Override
    //reduces inputted power and sends to turret subsystem
    public void execute() {
        turret.runMotor(
			trapezoidal.calculate(
				-Math.pow(operator.getTwist(),Turret.TRAVERSE_POWER
					)/2 // prevent over-speed
				)
			);
    }
}
