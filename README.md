# WIP


# TEAM 2502 *insert robot name here* ROBOT CODE

## CODE
This is the robot code that is run on Talon Robotics 2022 robot. The robot uses command based programming, meaning we have subsystems and commands.

## SUBSYSTEMS

### Climber Subsystem
This is the subsystem that controls the winches and the pneumatics for the climber. Our climber uses pistons to clamp on the static climbers, and pistons to
tilt the climber backwards to grab onto another bar.

### Drivetrain Subsystem
The drive train subsystem controls four Talon 500 motors that move the robot. We are using two speed gearboxes, changing gears using a pneumatic piston that is
a toggle in code. There are encoders on the motors so that we can count the ticks and convert those rotations to the wheel rpm, converted to inches traveled by the
encoder ticks. ~1033 ticks is one inch.

### Intake Subsystem
The intake subsystem controls a Neo, and two baby Neos. The squeeze wheels are mounted with the top belt so the hopper is also in this subsystem. The Neo runs the
active intake, when the baby Neos power the top belt, that also has the squeeze wheels, and the bottom belt.

### Shooter Subsystem
The shooter subsystem controls two Neos that spin the flywheel extremely fast. There is a PID controller that has our flywheel spin at a select rotations per
minute.

### Turret Subsystem
The turret subsystem controls a baby Neo that turns a gear connected to the turret's sprocket.

### Vision Subsystem
The vision subsystem is what's responsible for finding and tracking the hoop.

## COMMANDS

### Drive Command
The drive command is responsible for making the robot move.

### Go Command
The go command can make the robot move a certain amount of inches based of data from the drivetrain encoders. Using an encoder to get. This command can be called in
autonomous to move a distance when called.

### Run Intake Command
The run intake command runs the intake and the hopper.

### Run Shooter Manual Command
The amazing run shooter manual command can spin the flywheel at an RPM that is set by the slider on the operator joystick.

### Shift Command
The shift command shifts the gears on the drivetrain.

### Shoot Command
The shoot command controls the rollers that push the balls into the flywheel. It's also responsible for turning the hopper to load the next ball into the rollers.

### Toggle Intake Command
The toggle intake command is responsible for the two pneumatic pistons that move the intake in and out.

### Turn Angle Command
The turn angle command turns the robot to a specific angle using the navX. This command can be called in auto to turn an angle. This command uses a PID controller.

### Turn Turret Command
The turn turret command turns the turret based on the operators twist.

### Vision Align Drivetrain
The Vision align drivetrain command is from last seasons robot, the command aligns the drivetrain to aim at a target from the limelight.

### Vision Align Turret
The vision align turret command is responsible for turing the turret to auto aim at the hoop. This command can be called in auto to shoot a ball in the hoop.

### Vision Go To Ball Command
The vision go to ball command makes the robot go to a ball that is the correct color seen by the camera mounted on the front of the robot. This command is used in
auto to go to a ball.
