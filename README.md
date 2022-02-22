# TEAM 2502 *insert robot name here* ROBOT CODE

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
## CODE
This is the robot code that is run on Talon Robotics 2022 robot. The robot uses command based programming, meaning we have subsystems and commands.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
## SUBSYSTEMS

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Climber Subsystem
This is the subsystem that controlles the wenches and the pheumatics for the climber. Our climber uses pistions to clamp on the static climbers, and pistons to tilt the climber backwards to grab onto another bar.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Drivetrain Subsystem
The drive train subsystem controlles four talon 500 motors that move the robot. We are using two speed gearboxes, changing gears using a phenumatic pisiton that is  a toggle in code.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Intake Subsystem
The intake subsystem controlles a neo, and two baby neos. The squeze wheels are mounted with the top belt so the hopper is also in this subsystem. The neo runs the active intake, when the baby neos power the top belt, that also has the squeeze wheels, and the bottom belt.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Shooter Subsystem
The shooter subsystem controlls two neos that spin the flywheel extremly fast. There is a PID controller that has our flywheel spin at a select rotations per minute.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Turret Subsystem
The turret subsystem controlles a baby neo that turns a gear connected to the turrets sproket.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Vision Subsystem
The vision subsystem is whats responsable for finding and tracking the hoop.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
## COMMANDS

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Drive Command
The drive command is responsable for making the robot move.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Go Command
The go command can make the robot move a certian amount of inces based of data from the drivetrain encoders. Using an encoder to get. This command can be called in automous to move a distace when called.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Run Intake Command
the run intake command runs the intake and the hopper.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Run Shooter Manual Command
The amazing rin shooter manual command can spin the flywheel at an rpm that is set by the slider on the operator joystick.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Shift Command
The shift command shifts the gears on the drivetrain.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Shoot Command
the shoot command controlles the rollers that push the balls into the flywheel. Its also responsable for turning the hopper to load the next ball into the rollers.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Toggle Intake Command
The toggle intake command is responsable for the two phenumatic pistons that move the intake in and out.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Turn Angle Command
The turn angle command turns the robot to a specific angle using the navX. This command can be called in auto to turn an angle. This command uses a PID controller.

![Winning!!!!](https://drive.google.com/uc?id=1FJ24piuOmyX83aRY6UMdU286dqvR5mIR)
### Turn Turret Command
The turn turret command turns the turret based on the operators twist.

### Vision Align Drivetrain

### Vision Align Turret

### Vision Go To Ball Command

### Vision Un Align Turret
