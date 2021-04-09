package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.AlignToTarget;
import frc.robot.utils.CustomRamseteCommand;
import frc.robot.subsystems.*;
import frc.robot.commands.intake.AutoIntake;
import frc.robot.utils.RamseteGenerator;

import java.util.List;

public class GalacticSearchB extends SequentialCommandGroup {

    public GalacticSearchB(Drivetrain drivetrain, Shooter shooter, Intake intake, Conveyor conveyor){
        CustomRamseteCommand intakeDrop1 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.1), Units.feetToMeters(7.06), new Rotation2d().fromDegrees(0.00)),
		                new Pose2d(Units.feetToMeters(4.8), Units.feetToMeters(7.06), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(20), false
                );

        CustomRamseteCommand intakeDrop2 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(4.8), Units.feetToMeters(7.06), new Rotation2d().fromDegrees(0.00)),
		                new Pose2d(Units.feetToMeters(2.1), Units.feetToMeters(7.06), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(20), true
                );

        CustomRamseteCommand galacticSearchBPath =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.05), Units.feetToMeters(7.06), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(7.44), Units.feetToMeters(10.00), new Rotation2d().fromDegrees(45.00)),
                                new Pose2d(Units.feetToMeters(12.49), Units.feetToMeters(5.67), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(17.30), Units.feetToMeters(10.05), new Rotation2d().fromDegrees(90.00)),
                                new Pose2d(Units.feetToMeters(27.91), Units.feetToMeters(7.72), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        addCommands(
                sequence(
                        new InstantCommand(() -> drivetrain.resetOdometry(galacticSearchBPath.getInitialPose())),
                        intakeDrop1.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        intakeDrop2.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        deadline(
                                galacticSearchBPath,
                                new AutoIntake(shooter, intake, conveyor, AutoIntake.IntakeType.INTAKE)
                        )
                )
        );

    }

}
