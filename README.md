# TEAM 2502 *insert robot name here* ROBOT CODE

## CODE
This is the robot code that is run on Talon Robotics 2022 robot. The robot uses command based programming, meaning we have subsystems and commands.

## SUBSYSTEMS

### Climber Subsystem
This is the subsystem that controlles the wenches and the pheumatics for the climber. Our climber uses pistions to clamp on the static climbers, and pistons to tilt the climber backwards to grab onto another bar.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Drivetrain Subsystem
The drive train subsystem controlles four talon 500 motors that move the robot. We are using two speed gearboxes, changing gears using a phenumatic pisiton that is  a toggle in code.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Intake Subsystem
The intake subsystem controlles a neo, and two baby neos. The squeze wheels are mounted with the top belt so the hopper is also in this subsystem. The neo runs the active intake, when the baby neos power the top belt, that also has the squeeze wheels, and the bottom belt.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Shooter Subsystem
The shooter subsystem controlls two neos that spin the flywheel extremly fast. There is a PID controller that has our flywheel spin at a select rotations per minute.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Turret Subsystem
The turret subsystem controlles a baby neo that turns a gear connected to the turrets sproket.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Vision Subsystem
The vision subsystem is whats responsable for finding and tracking the hoop.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
## COMMANDS

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Drive Command
The drive command is responsable for making the robot move.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Go Command
The go command can make the robot move a certian amount of inces based of data from the drivetrain encoders. Using an encoder to get. This command can be called in automous to move a distace when called.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Run Intake Command
the run intake command runs the intake and the hopper.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Run Shooter Manual Command
The amazing rin shooter manual command can spin the flywheel at an rpm that is set by the slider on the operator joystick.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Shift Command
The shift command shifts the gears on the drivetrain.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Shoot Command
the shoot command controlles the rollers that push the balls into the flywheel. Its also responsable for turning the hopper to load the next ball into the rollers.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Toggle Intake Command
The toggle intake command is responsable for the two phenumatic pistons that move the intake in and out.

![Alt Text](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5wSN4jp8d4ws9h8noyEW_bLQOL9i51jIKU7RMupi1Ztl22vleV-aQJyCCDlq4Rb-Bwx4:https://i.pinimg.com/originals/48/af/d0/48afd0510b98ad1202daaee5bf28bc4c.gif)
### Turn Angle Command

### Turn Turret Command

### Vision Align Drivetrain

### Vision Align Turret

### Vision Go To Ball Command

### Vision Un Align Turret
