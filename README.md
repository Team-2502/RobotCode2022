# TEAM 2502 *insert robot name here* ROBOT CODE

## CODE
This is the robot code that is run on Talon Robotics 2022 robot. The robot uses command based programming, meaning we have subsystems and commands.

## SUBSYSTEMS

### Climber Subsystem
This is the subsystem that controlles the wenches and the pheumatics for the climber. Our climber uses pistions to clamp on the static climbers, and pistons to tilt the climber backwards to grab onto another bar.

### Drivetrain Subsystem
The drive train subsystem controlles four talon 500 motors that move the robot. We are using two speed gearboxes, changing gears using a phenumatic pisiton that is  a toggle in code.

### Intake Subsystem
The intake subsystem controlles a neo, and two baby neos. The squeze wheels are mounted with the top belt so the hopper is also in this subsystem. The neo runs the active intake, when the baby neos power the top belt, that also has the squeeze wheels, and the bottom belt.

### Shooter Subsystem
The shooter subsystem controlls two neos that spin the flywheel extremly fast. There is a PID controller that has our flywheel spin at a select rotations per minute.

### Turret Subsystem
The turret subsystem controlles a baby neo that turns a gear connected to the turrets sproket.

### Vision Subsystem
The vision subsystem is whats responsable for finding and tracking the hoop.

## COMMANDS

### Drive Command
The drive command is responsable for making the robot move.

### Go Command
The go command can make the robot move a certian amount of inces based of data from the drivetrain encoders. Using an encoder to get. This command can be called in automous to move a distace when called.

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

### Turn Turret Command

### Vision Align Drivetrain

### Vision Align Turret

### Vision Go To Ball Command

### Vision Un Align Turret
