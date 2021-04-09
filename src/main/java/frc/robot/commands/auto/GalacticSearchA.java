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

public class GalacticSearchA extends SequentialCommandGroup {

    public GalacticSearchA(Drivetrain drivetrain, Shooter shooter, Intake intake, Conveyor conveyor){
        CustomRamseteCommand intakeDrop1 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.05), Units.feetToMeters(8.06), new Rotation2d().fromDegrees(0.00)),
		                new Pose2d(Units.feetToMeters(4.8), Units.feetToMeters(8.06), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(20), false
                );

        CustomRamseteCommand intakeDrop2 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(4.8), Units.feetToMeters(8.06), new Rotation2d().fromDegrees(0.00)),
		                new Pose2d(Units.feetToMeters(2.4), Units.feetToMeters(8.06), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(20), true
                );

        CustomRamseteCommand galacticSearchAPath =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.00), Units.feetToMeters(8.06), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(12.06), Units.feetToMeters(5.31), new Rotation2d().fromDegrees(-30.00)),
                                new Pose2d(Units.feetToMeters(15.06), Units.feetToMeters(11.16), new Rotation2d().fromDegrees(90.00)),
                                new Pose2d(Units.feetToMeters(27.47), Units.feetToMeters(8.18), new Rotation2d().fromDegrees(0.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        addCommands(
                sequence(
                        new InstantCommand(() -> drivetrain.resetOdometry(galacticSearchAPath.getInitialPose())),
                        intakeDrop1.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        intakeDrop2.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        deadline(
                                galacticSearchAPath,
                                new AutoIntake(shooter, intake, conveyor, AutoIntake.IntakeType.INTAKE)
                        )
                )
        );

    }

}
