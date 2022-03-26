package com.team2502.robot2022.commands;

import com.team2502.robot2022.Constants;
import com.team2502.robot2022.subsystems.IntakeSubsystem;
import com.team2502.robot2022.subsystems.OdometrySubsystem;
import com.team2502.robot2022.util.Ball;
import com.team2502.robot2022.util.Ball.BallColor;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeOdometryCommand extends CommandBase {

    OdometrySubsystem odometry;
    IntakeSubsystem intake;

    private boolean intakeRising;

    /**
    * adds or removes balls from the {@link java.util.Deque} using the intake color sensor
    * @param intake
    * @param odometry
     */
    public IntakeOdometryCommand(IntakeSubsystem intake, OdometrySubsystem odometry){
       this.odometry = odometry;
       this.intake = intake;
    }

    @Override
    public void initialize() {
        intakeRising = false;
    }

    @Override
    public void execute() {
        if (intake.ballBySensor() && !intakeRising) { // only run for newly detected balls
            intakeRising = true;

            if (
                intake.getBeltSpeed() > 0 && // belt moving inward
                (odometry.hopperContents.peekFirst().detectedPos - intake.getBeltPos() 
                > Constants.Subsystem.Hopper.BALL_LENGTH_ROTATIONS // not a known ball
                || odometry.hopperContents.isEmpty())
                ) { // belt moving forward, no detected ball in sensor zone

                // store color
                BallColor color;
                if (intake.hasRedBall()) {
                    color = BallColor.RED;
                } else if (intake.hasBlueBall()) {
                    color = BallColor.BLUE;
                } else {
                    color = BallColor.UNKNOWN;
                }

                // create new ball
                Ball ball = new Ball(color,intake.getBeltPos());

                // add ball to odometry
                odometry.hopperContents.push(ball);

            } else if (intake.getBeltSpeed() < 0) { // belt is moving outward, and there is a ball in the sensor zone
                odometry.hopperContents.pop(); // remove foremost ball
            }
        } else { intakeRising = false; }
    }
}
