package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.AlignToTarget;
import frc.robot.utils.CustomRamseteCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.utils.RamseteGenerator;

import java.util.List;

public class BouncePath extends SequentialCommandGroup {

    public BouncePath(Drivetrain drivetrain){
        CustomRamseteCommand bouncePath1 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.60), Units.feetToMeters(7.77), new Rotation2d().fromDegrees(0.00)),
		                new Pose2d(Units.feetToMeters(7.49), Units.feetToMeters(11.2), new Rotation2d().fromDegrees(90.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        CustomRamseteCommand bouncePath2 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(7.49), Units.feetToMeters(11.2), new Rotation2d().fromDegrees(90.00)),
                                new Pose2d(Units.feetToMeters(12.5), Units.feetToMeters(3.20), new Rotation2d().fromDegrees(-215.00)),
                                new Pose2d(Units.feetToMeters(15.02), Units.feetToMeters(11.4), new Rotation2d().fromDegrees(-90.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), true
                );

        CustomRamseteCommand bouncePath3 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(15.02), Units.feetToMeters(11.4), new Rotation2d().fromDegrees(-90.00)),
                                new Pose2d(Units.feetToMeters(18.86), Units.feetToMeters(2.60), new Rotation2d().fromDegrees(0.46)),
                                new Pose2d(Units.feetToMeters(22.55), Units.feetToMeters(11.2), new Rotation2d().fromDegrees(90.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        CustomRamseteCommand bouncePath4 =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(22.55), Units.feetToMeters(11.2), new Rotation2d().fromDegrees(90.00)),
		                new Pose2d(Units.feetToMeters(27.60), Units.feetToMeters(7.58), new Rotation2d().fromDegrees(180.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), true
                );

        addCommands(
                sequence(
                        new InstantCommand(() -> drivetrain.resetOdometry(bouncePath1.getInitialPose())),
                        bouncePath1.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        bouncePath2.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        bouncePath3.andThen(() -> drivetrain.tankDriveVolts(0, 0)),
                        bouncePath4.andThen(() -> drivetrain.tankDriveVolts(0, 0))
                )
        );

    }

}
