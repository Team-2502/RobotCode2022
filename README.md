# WIP

# TEAM 2502 *insert robot name here* ROBOT CODE

## CODE
This is the robot code that is run on Talon Robotics 2022 robot. The robot uses command based programming, meaning we have subsystems and commands.

## SUBSYSTEMS

### Climber Subsystem
This is the subsystem that controlles the wenches and the pheumatics for the climber. Our climber uses pistions to clamp on the static climbers, and pistons to \
tilt the climber backwards to grab onto another bar.

### Drivetrain Subsystem
The drive train subsystem controlles four talon 500 motors that move the robot. We are using two speed gearboxes, changing gears using a phenumatic pisiton that is 
a toggle in code.

### Intake Subsystem
The intake subsystem controlles a neo, and two baby neos. The squeze wheels are mounted with the top belt so the hopper is also in this subsystem. The neo runs the 
active intake, when the baby neos power the top belt, that also has the squeeze wheels, and the bottom belt.

### Shooter Subsystem
The shooter subsystem controlls two neos that spin the flywheel extremly fast. There is a PID controller that has our flywheel spin at a select rotations per 
minute.

### Turret Subsystem
The turret subsystem controlles a baby neo that turns a gear connected to the turrets sproket.

### Vision Subsystem
The vision subsystem is whats responsable for finding and tracking the hoop.

## COMMANDS

### Drive Command
The drive command is responsable for making the robot move.

### Go Command
The go command can make the robot move a certian amount of inces based of data from the drivetrain encoders. Using an encoder to get. This command can be called in 
automous to move a distace when called.

### Run Intake Command
the run intake command runs the intake and the hopper.

### Run Shooter Manual Command
The amazing rin shooter manual command can spin the flywheel at an rpm that is set by the slider on the operator joystick.

### Shift Command
The shift command shifts the gears on the drivetrain.

### Shoot Command
the shoot command controlles the rollers that push the balls into the flywheel. Its also responsable for turning the hopper to load the next ball into the rollers.

### Toggle Intake Command
The toggle intake command is responsable for the two phenumatic pistons that move the intake in and out.

### Turn Angle Command
The turn angle command turns the robot to a specific angle using the navX. This command can be called in auto to turn an angle. This command uses a PID controller.

### Turn Turret Command
The turn turret command turns the turret based on the operators twist.

### Vision Align Drivetrain
The Vision align drivetrain command is from last seasons robot, the command aligns the drivetrain to aim at a target from the limelight.

### Vision Align Turret
The vision align turret command is responsable for turing the turret to auto aim at the hoop. This command can be called in auto to shoot a ball in the hoop.

### Vision Go To Ball Command
The vision go to ball command makes the robot go to a ball that is the correct color seen by the camera mounted on the front of the robot. This command is used in 
auto to go to a ball.


## ALL THE CODE EXPLAINED

### Drive Command

    @Override
    public void initialize()
    {
        drivetrain.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void execute() {
        switch(typeEntry.getSelected()) {
            case Tank:
                drivetrain.getDrive().tankDrive(rightJoystick.getY(), -leftJoystick.getY(), true);
                break;
            case Arcade:
                drivetrain.getDrive().arcadeDrive(rightJoystick.getX(), leftJoystick.getY(), true);
                break;
            case Reverse:
                drivetrain.getDrive().tankDrive(leftJoystick.getY(), rightJoystick.getY(), true);
        }
    }
    private enum Drivetype {
        Tank,
        Arcade,
        Reverse
    }

In the subsystem, during initialization we set the coast mode to brake. The two options for this are coast and brake, coast makes the motors slowly spin to a stop 
after the command is done. When brake uses power to stop the motor fast.

In the drive command we have 3 differnt ways of driving, tank, arcade, and reverse. Tank drive uses the left joystick to control the left motors of the drivetrain 
when the right controls the right. Arcade uses the left joystick to move forward and backward, when the right joystick turns the robot. Reverse is the same as 
arcade but the joysticks are flipped.

### Go command

	@Override
	public void initialize() {
	this.startPos = drivetrain.getInchesTraveled();
	this.pid = new PIDController(0.02,0.03,0.03);
	this.trapezoidal = new Trapezoidal(.6);
	pid.setTolerance(.2);

	pid.reset();
	trapezoidal.reset();
    }

    @Override
    public void execute() {
	    double error = (drivetrain.getInchesTraveled()-startPos)+goalPoint;
	    //double speed = pid.calculate(error);
	    double speed = trapezoidal.calculate(pid.calculate(error));
        speed = constrain(speed,.3);
	    drivetrain.setSpeed(-speed,speed);
    }

    @Override
    public boolean isFinished() {
	    return pid.atSetpoint();
    }

The go command uses a pid and trapazoidal to turn the motors percisly and efficintly. The go command uses the encoder ticks from the drivetrain that is converted 
to inches so we can track how far the robot moves. We take the encoder ticks, and since we cant reset the ticks we add our target ticks to the current ticks so we 
know how many ticks we need to go to to have moved the selected amount of inces. This command is used in auto.

### Run intake command

    public RunIntakeCommand(IntakeSubsystem intake, double speedIntake, double speedBelt) {
        this.intake = intake;
        this.speedIntake = speedIntake;
        this.speedBelt = speedBelt;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.run(speedIntake, speedBelt);
    }

    @Override
    public void end(boolean kInterrupted) {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

The run intake command runs the intake and the belts because the squeeze wheels are connected to the top belt, so we are just going to spin the bottom belt as 
well. We call the method with speeds for both the intake and belts. The top belt and the bottom belt run at the same speed. The intake is running fast enough that 
when a ball touches it it will suck it up, but not too fast that the ball is shot through the hopper. The stop method in the intake subsystem stops all 3 motors.

## Run Shooter Manual Command

    @Override
    public void initialize() {
        shooter.setShooterSpeedRPM(0); // stop on init
        turret.stop();
    }

    @Override
    public void execute() {
        double speedInput = controlJoystick.getThrottle(); // get slider value
        double targetRpm = (1-speedInput)*defaultSpeed; // value decreases as you move the slider up, ranges from -1 to 1 (mapped to 0-2)
        shooter.setShooterSpeedRPM(targetRpm);

        turret.runMotor(Math.pow(-controlJoystick.getTwist(),Turret.TRAVERSE_POWER));
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter(); // this will coast
        turret.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
